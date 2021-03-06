package com.checkmarx.flow.service;

import com.checkmarx.configprovider.ConfigProvider;
import com.checkmarx.flow.config.FindingSeverity;
import com.checkmarx.flow.config.FlowProperties;
import com.checkmarx.flow.config.external.ASTConfig;
import com.checkmarx.flow.constants.FlowConstants;
import com.checkmarx.flow.dto.BugTracker;
import com.checkmarx.flow.dto.ControllerRequest;
import com.checkmarx.flow.dto.FlowOverride;
import com.checkmarx.flow.dto.ScanRequest;
import com.checkmarx.flow.utils.ScanUtils;
import com.checkmarx.sdk.config.AstProperties;
import com.checkmarx.sdk.config.CxProperties;
import com.checkmarx.sdk.config.ScaConfig;
import com.checkmarx.sdk.config.ScaProperties;
import com.checkmarx.sdk.dto.CxConfig;
import com.checkmarx.sdk.dto.Sca;
import com.checkmarx.sdk.dto.filtering.FilterConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfigurationOverrider {
    private static final Set<BugTracker.Type> bugTrackersForPullRequest = new HashSet<>(Arrays.asList(
            BugTracker.Type.ADOPULL,
            BugTracker.Type.BITBUCKETPULL,
            BugTracker.Type.BITBUCKETSERVERPULL,
            BugTracker.Type.GITHUBPULL,
            BugTracker.Type.GITLABMERGE));

    private final FlowProperties flowProperties;
    private final ScaProperties scaProperties;
    private final SCAScanner scaScanner;
    private final SastScanner sastScanner;

    public ScanRequest overrideScanRequestProperties(CxConfig override, ScanRequest request) {
        ConfigProvider configProvider = ConfigProvider.getInstance();
        if (request == null || (!isConfigAsCodeAvailable(configProvider) && !isLegacyConfigAsCodeAvailable(override))) {
            return request;
        }

        Map<String, String> overrideReport = new HashMap<>();
        overrideMainProperties(Optional.ofNullable(override), request, overrideReport);

        try {
            Optional.ofNullable(override)
                .map(CxConfig::getAdditionalProperties).ifPresent(ap -> {
                Object flow = ap.get("cxFlow");
                ObjectMapper mapper = new ObjectMapper();
                Optional.ofNullable(mapper.convertValue(flow, FlowOverride.class)).ifPresent(flowOverride ->
                    applyFlowOverride(flowOverride, request, overrideReport)
                );

            });

            String overriddenProperties = convertMapToString(overrideReport);
            log.info("The following properties were overridden by config-as-code file: {}", overriddenProperties);

        } catch (IllegalArgumentException e) {
            log.warn("Error parsing cxFlow object from CxConfig.", e);
        }
        return request;
    }

    private boolean isConfigAsCodeAvailable(ConfigProvider configProvider) {
        return configProvider.hasAnyConfiguration(MDC.get(FlowConstants.MAIN_MDC_ENTRY));
    }

    private boolean isLegacyConfigAsCodeAvailable(CxConfig override) {
        return override != null && !(Boolean.FALSE.equals(override.getActive()));
    }

    private void applyFlowOverride(FlowOverride fo, ScanRequest request, Map<String, String> overrideReport) {
        BugTracker bt = getBugTracker(fo, request, overrideReport);
        /*Override only applicable to Simple JIRA bug*/
        if (BugTracker.Type.JIRA.equals(bt.getType()) && fo.getJira() != null) {
            overrideJiraBugProperties(fo, bt);
        }

        request.setBugTracker(bt);

        Optional.ofNullable(fo.getApplication())
                .filter(StringUtils::isNotBlank)
                .ifPresent(a -> {
                    request.setApplication(a);
                    overrideReport.put("application", a);
                });

        Optional.ofNullable(fo.getBranches())
                .filter(CollectionUtils::isNotEmpty)
                .ifPresent(br -> {
                    request.setActiveBranches(br);
                    overrideReport.put("active branches", Arrays.toString(br.toArray()));
                });

        Optional.ofNullable(fo.getEmails())
                .ifPresent(e -> request.setEmail(e.isEmpty() ? null : e));

        overrideFilters(fo, request, overrideReport);

        overrideThresholds(fo, overrideReport, request);

        Optional.ofNullable(fo.getVulnerabilityScanners()).ifPresent(vulnerabilityScanners -> {
            List<VulnerabilityScanner> scanRequestVulnerabilityScanner = new ArrayList<>();

            vulnerabilityScanners.forEach(vs -> {
                if (vs.equalsIgnoreCase(ScaProperties.CONFIG_PREFIX)) {
                    scanRequestVulnerabilityScanner.add(scaScanner);
                }
                if (vs.equalsIgnoreCase(CxProperties.CONFIG_PREFIX)) {
                    scanRequestVulnerabilityScanner.add(sastScanner);
                }
            });
            request.setVulnerabilityScanners(scanRequestVulnerabilityScanner);
            overrideReport.put("vulnerabilityScanners", vulnerabilityScanners.toString());
        });

    }

    private void overrideThresholds(FlowOverride flowOverride, Map<String, String> overrideReport, ScanRequest request) {
        Optional.ofNullable(flowOverride.getThresholds()).ifPresent(thresholds -> {
            Map<FindingSeverity, Integer> thresholdsMap = getThresholdsMap(thresholds);
            if (!thresholdsMap.isEmpty()) {
                request.setThresholds(thresholdsMap);
                overrideReport.put("thresholds", convertMapToString(thresholdsMap));
            }
        });
    }

    private void overrideFilters(FlowOverride flowOverride, ScanRequest request, Map<String, String> overrideReport) {
        Optional.ofNullable(flowOverride.getFilters()).ifPresent(override -> {
            FilterFactory filterFactory = new FilterFactory();
            ControllerRequest controllerRequest = new ControllerRequest(override.getSeverity(),
                    override.getCwe(),
                    override.getCategory(),
                    override.getStatus());
            FilterConfiguration filter = filterFactory.getFilter(controllerRequest, null);
            request.setFilter(filter);

            String filterDescr;
            if (CollectionUtils.isNotEmpty(filter.getSimpleFilters())) {
                filterDescr = filter.getSimpleFilters()
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));
            } else {
                filterDescr = "EMPTY";
            }
            overrideReport.put("filters", filterDescr);
        });
    }

    private void overrideMainProperties(Optional<CxConfig> override, ScanRequest request, Map<String, String> overrideReport) {
        override.map(CxConfig::getProject)
                .filter(StringUtils::isNotBlank)
                .ifPresent(p -> {
                    /*Replace ${repo} and ${branch}  with the actual reponame and branch - then strip out non-alphanumeric (-_ are allowed)*/
                    String project = p.replace("${repo}", request.getRepoName())
                            .replace("${branch}", request.getBranch())
                            .replaceAll("[^a-zA-Z0-9-_.]+", "-");
                    request.setProject(project);
                    overrideReport.put("project", project);
                });
        override.map(CxConfig::getTeam)
                .filter(StringUtils::isNotBlank)
                .ifPresent(t -> {
                    request.setTeam(t);
                    overrideReport.put("team", t);
                });
        override.map(CxConfig::getSast).ifPresent(s -> {
            Optional.ofNullable(s.getIncremental()).ifPresent(si -> {
                request.setIncremental(si);
                overrideReport.put("incremental", si.toString());
            });

            Optional.ofNullable(s.getForceScan()).ifPresent(sf -> {
                request.setForceScan(sf);
                overrideReport.put("force scan", sf.toString());
            });

            Optional.ofNullable(s.getPreset()).ifPresent(sp -> {
                request.setScanPreset(sp);
                request.setScanPresetOverride(true);
                overrideReport.put("scan preset", sp);
            });
            Optional.ofNullable(s.getFolderExcludes()).ifPresent(sfe -> {
                request.setExcludeFolders(Arrays.asList(sfe.split(",")));
                overrideReport.put("exclude folders", sfe);
            });
            Optional.ofNullable(s.getFileExcludes()).ifPresent(sf -> {
                request.setExcludeFiles(Arrays.asList(sf.split(",")));
                overrideReport.put("exclude files", sf);
            });
        });
        ConfigProvider configProvider = ConfigProvider.getInstance();
        String uid = MDC.get(FlowConstants.MAIN_MDC_ENTRY);
        ScaConfig scaConfiguration = configProvider.getConfiguration(uid, ScaProperties.CONFIG_PREFIX, ScaConfig.class);
        if (scaConfiguration != null) {
            log.info("Overriding SCA properties from config provider configuration");
            overridePropertiesSca(scaConfiguration, overrideReport, request);
        } else {
            overridePropertiesSca(override.map(CxConfig::getSca), overrideReport, request);
        }

        ASTConfig astConfiguration = configProvider.getConfiguration(uid, AstProperties.CONFIG_PREFIX, ASTConfig.class);
        if (astConfiguration != null) {
            log.info("Overriding AST properties from config provider configuration");
            overridePropertiesAst(astConfiguration, overrideReport, request);
        }
    }

    private void overridePropertiesAst(ASTConfig astConfiguration, Map<String, String> overrideReport, ScanRequest request) {
        setOverriderReportWithASTProperties(astConfiguration, overrideReport);
        request.setAstConfig(astConfiguration);
    }

    private void overridePropertiesSca(ScaConfig scaConfiguration, Map<String, String> overrideReport, ScanRequest request) {
        setOverriderReportWithScaProperties(scaConfiguration, overrideReport);
        request.setScaConfig(scaConfiguration);
    }

    private void setOverriderReportWithASTProperties(ASTConfig astConfiguration, Map<String, String> overrideReport) {
        overrideReport.put("AST apiUrl", astConfiguration.getApiUrl());
        overrideReport.put("AST preset", astConfiguration.getPreset());
        overrideReport.put("AST incremental", String.valueOf(astConfiguration.isIncremental()));
    }

    private void setOverriderReportWithScaProperties(ScaConfig scaConfiguration, Map<String, String> overrideReport) {
        overrideReport.put("accessControlUrl", scaConfiguration.getAccessControlUrl());
        overrideReport.put("apiUrl", scaConfiguration.getApiUrl());
        overrideReport.put("appUrl", scaConfiguration.getAppUrl());
        overrideReport.put("tenant", scaConfiguration.getTenant());
        overrideReport.put("thresholdsSeverity", convertMapToString(scaConfiguration.getThresholdsSeverity()));
        overrideReport.put("thresholdsScore", String.valueOf(scaConfiguration.getThresholdsScore()));
        overrideReport.put("filterSeverity", scaConfiguration.getFilterSeverity().toString());
        overrideReport.put("filterScore", String.valueOf(scaConfiguration.getFilterScore()));
    }

    private void overridePropertiesSca(Optional<Sca> sca, Map<String, String> overrideReport, ScanRequest request) {
        if (!sca.isPresent()) {
          return;
        }

        ScaConfig scaConfig = ScaConfig.builder()
                .build();

        sca.map(Sca::getAccessControlUrl).ifPresent(accessControlUrl -> {
            scaConfig.setAccessControlUrl(accessControlUrl);
            overrideReport.put("accessControlUrl", accessControlUrl);
        });

        sca.map(Sca::getApiUrl).ifPresent(apiUrl -> {
            scaConfig.setApiUrl(apiUrl);
            overrideReport.put("apiUrl", apiUrl);
        });

        sca.map(Sca::getAppUrl).ifPresent(appUrl -> {
            scaConfig.setAppUrl(appUrl);
            overrideReport.put("appUrl", appUrl);
        });

        sca.map(Sca::getTenant).ifPresent(tenant -> {
            scaConfig.setTenant(tenant);
            overrideReport.put("tenant", tenant);
        });

        sca.map(Sca::getThresholdsSeverity).ifPresent(thresholdsSeverity -> {
            scaConfig.initThresholdsSeverity(thresholdsSeverity);
            overrideReport.put("thresholdsSeverity", convertMapToString(thresholdsSeverity));
        });

        sca.map(Sca::getThresholdsScore).ifPresent(thresholdsScore -> {
            scaConfig.setThresholdsScore(thresholdsScore);
            overrideReport.put("thresholdsScore", String.valueOf(thresholdsScore));
        });

        sca.map(Sca::getFilterSeverity).ifPresent(filterSeverity -> {
            scaConfig.setFilterSeverity(filterSeverity);
            overrideReport.put("filterSeverity", filterSeverity.toString());
        });

        sca.map(Sca::getFilterScore).ifPresent(filterScore -> {
            scaConfig.setFilterScore(filterScore);
            overrideReport.put("filterScore", String.valueOf(filterScore));
        });

        request.setScaConfig(scaConfig);
    }

    /**
     * Override scan request details as per file/blob (MachinaOverride)
     */
    public ScanRequest overrideScanRequestProperties(FlowOverride override, ScanRequest request) {
        if (override == null) {
            return request;
        }

        BugTracker bt = request.getBugTracker();
        /*Override only applicable to Simple JIRA bug*/
        if (request.getBugTracker().getType().equals(BugTracker.Type.JIRA) && override.getJira() != null) {
            overrideJiraBugProperties(override, bt);
        }
        request.setBugTracker(bt);

        if (!ScanUtils.empty(override.getApplication())) {
            request.setApplication(override.getApplication());
        }

        if (!ScanUtils.empty(override.getBranches())) {
            request.setActiveBranches(override.getBranches());
        }

        List<String> emails = override.getEmails();
        if (emails != null) {
            if (emails.isEmpty()) {
                request.setEmail(null);
            } else {
                request.setEmail(emails);
            }
        }
        FlowOverride.Filters filtersObj = override.getFilters();

        if (filtersObj != null) {
            FilterFactory filterFactory = new FilterFactory();
            ControllerRequest controllerRequest = new ControllerRequest(filtersObj.getSeverity(),
                    filtersObj.getCwe(),
                    filtersObj.getCategory(),
                    filtersObj.getStatus());
            FilterConfiguration filter = filterFactory.getFilter(controllerRequest, null);
            request.setFilter(filter);
        }

        return request;
    }

    private BugTracker getBugTracker(FlowOverride override, ScanRequest request, Map<String, String> overridingReport) {
        BugTracker result;
        if (request.getBugTracker() == null) {
            result = BugTracker.builder()
                    .type(BugTracker.Type.NONE)
                    .build();
            log.debug("Bug tracker is not specified in scan request. Setting bug tracker type to '{}'.", result.getType());
        } else {
            result = request.getBugTracker();
        }

        if (canOverrideBugTracker(result, override)) {
            String bugTrackerNameOverride = override.getBugTracker();
            log.debug("Overriding '{}' bug tracker with '{}'.", result.getType(), bugTrackerNameOverride);
            BugTracker.Type bugTrackerTypeOverride = ScanUtils.getBugTypeEnum(bugTrackerNameOverride, flowProperties.getBugTrackerImpl());

            BugTracker.BugTrackerBuilder builder = BugTracker.builder()
                    .type(bugTrackerTypeOverride);

            if (bugTrackerTypeOverride.equals(BugTracker.Type.CUSTOM)) {
                builder.customBean(bugTrackerNameOverride);
            }
            result = builder.build();
            overridingReport.put("bug tracker", bugTrackerNameOverride);
        }
        return result;
    }

    private static boolean canOverrideBugTracker(BugTracker bugTrackerFromScanRequest, FlowOverride override) {
        String bugTrackerNameOverride = override.getBugTracker();
        BugTracker.Type currentBugTrackerType = bugTrackerFromScanRequest.getType();

        boolean comingFromPullRequest = bugTrackersForPullRequest.contains(currentBugTrackerType);

        String cannotOverrideReason = null;

        if (comingFromPullRequest) {
            // Don't override bug tracker type if the scan is initiated by a pull request.
            // Otherwise bug tracker events won't be triggered.
            cannotOverrideReason = "scan was initiated by pull request";
        } else if (StringUtils.isEmpty(bugTrackerNameOverride)) {
            cannotOverrideReason = "no bug tracker override is defined";
        } else if (bugTrackerNameOverride.equalsIgnoreCase(currentBugTrackerType.toString())) {
            cannotOverrideReason = "bug tracker type in override is the same as in scan request";
        }

        if (cannotOverrideReason != null) {
            log.debug("Bug tracker override was not applied, because {}.", cannotOverrideReason);
        }

        return cannotOverrideReason == null;
    }

    private static String convertMapToString(Map<?, ?> map) {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }


    private static void overrideJiraBugProperties(FlowOverride override, BugTracker bt) {
        FlowOverride.Jira jira = override.getJira();
        if (!ScanUtils.empty(jira.getAssignee())) {
            bt.setAssignee(jira.getAssignee());
        }//if empty value override with null
        if (jira.getAssignee() != null && jira.getAssignee().isEmpty()) {
            bt.setAssignee(null);
        }
        if (!ScanUtils.empty(jira.getProject())) {
            bt.setProjectKey(jira.getProject());
        }
        if (!ScanUtils.empty(jira.getIssueType())) {
            bt.setIssueType(jira.getIssueType());
        }
        if (!ScanUtils.empty(jira.getOpenedStatus())) {
            bt.setOpenStatus(jira.getOpenedStatus());
        }
        if (!ScanUtils.empty(jira.getClosedStatus())) {
            bt.setClosedStatus(jira.getClosedStatus());
        }
        if (!ScanUtils.empty(jira.getOpenTransition())) {
            bt.setOpenTransition(jira.getOpenTransition());
        }
        if (!ScanUtils.empty(jira.getCloseTransition())) {
            bt.setCloseTransition(jira.getCloseTransition());
        }
        if (!ScanUtils.empty(jira.getCloseTransitionField())) {
            bt.setCloseTransitionField(jira.getCloseTransitionField());
        }
        if (!ScanUtils.empty(jira.getCloseTransitionValue())) {
            bt.setCloseTransitionValue(jira.getCloseTransitionValue());
        }
        if (jira.getFields() != null) { //if empty, assume no fields
            bt.setFields(jira.getFields());
        }
        if (jira.getPriorities() != null && !jira.getPriorities().isEmpty()) {
            bt.setPriorities(jira.getPriorities());
        }
    }

    private static Map<FindingSeverity, Integer> getThresholdsMap(FlowOverride.Thresholds thresholds) {
        Map<FindingSeverity, Integer> map = new EnumMap<>(FindingSeverity.class);
        if (thresholds.getHigh() != null) {
            map.put(FindingSeverity.HIGH, thresholds.getHigh());
        }
        if (thresholds.getMedium() != null) {
            map.put(FindingSeverity.MEDIUM, thresholds.getMedium());
        }
        if (thresholds.getLow() != null) {
            map.put(FindingSeverity.LOW, thresholds.getLow());
        }
        if (thresholds.getInfo() != null) {
            map.put(FindingSeverity.INFO, thresholds.getInfo());
        }

        return map;
    }
}
