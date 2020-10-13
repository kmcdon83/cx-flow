package com.checkmarx.flow.service;

import com.checkmarx.flow.config.FlowProperties;
import com.checkmarx.flow.dto.BugTracker;
import com.checkmarx.flow.dto.ScanDetails;
import com.checkmarx.flow.dto.ScanRequest;
import com.checkmarx.flow.utils.ScanUtils;
import com.checkmarx.sdk.config.CxGoProperties;
import com.checkmarx.sdk.config.CxProperties;
import com.checkmarx.sdk.config.CxPropertiesBase;
import com.checkmarx.sdk.dto.ScanResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BugTrackerEventTrigger {

    private static final String SCAN_MESSAGE = "Scan submitted to Checkmarx";
    private static final String SCAN_NOT_SUBMITTED_MESSAGE = "Scan not submitted to Checkmarx due to existing Active scan for the same project.";

    private final GitLabService gitLabService;
    private final GitHubService gitService;
    private final BitBucketService bbService;
    private final ADOService adoService;
    private final CxPropertiesBase cxPropertiesBase;

    public BugTrackerEventTrigger(GitLabService gitLabService, GitHubService gitService, BitBucketService bbService, ADOService adoService, FlowProperties flowProperties, CxProperties cxProperties, CxGoProperties cxgoProperties) {
        this.gitLabService = gitLabService;
        this.gitService = gitService;
        this.bbService = bbService;
        this.adoService = adoService;

        this.cxPropertiesBase = ScanUtils.getBaseProperties(flowProperties, cxgoProperties, cxProperties);
    }

    public BugTracker.Type triggerBugTrackerEvent(ScanRequest request) {
        boolean eventsWereTriggered = true;
        BugTracker.Type bugTrackerType = request.getBugTracker().getType();

        switch (bugTrackerType) {
            case GITLABMERGE:
                gitLabService.sendMergeComment(request, SCAN_MESSAGE);
                gitLabService.startBlockMerge(request);
                break;

            case GITLABCOMMIT:
                gitLabService.sendCommitComment(request, SCAN_MESSAGE);
                break;

            case GITHUBPULL:
                gitService.sendMergeComment(request, SCAN_MESSAGE);
                gitService.startBlockMerge(request, cxPropertiesBase.getUrl());
                break;

            case BITBUCKETPULL:
                bbService.sendMergeComment(request, SCAN_MESSAGE);
                break;

            case BITBUCKETSERVERPULL:
                bbService.sendServerMergeComment(request, SCAN_MESSAGE);
                bbService.setBuildStartStatus(request);
                break;

            case ADOPULL:
                adoService.sendMergeComment(request, SCAN_MESSAGE);
                adoService.startBlockMerge(request);
                break;

            case JIRA:
            case CUSTOM:
                eventsWereTriggered = false;
                break; // No action is needed

            default:
                eventsWereTriggered = false;
                log.warn("Bug-Tracker type: {} is not supported", bugTrackerType);
        }

        if (eventsWereTriggered) {
            log.debug("Completed triggering events for the '{}' bug tracker.", bugTrackerType);
        }
        else {
            log.debug("Bug tracker events were not triggered, because bug tracker type is '{}'.", bugTrackerType);
        }

        return bugTrackerType;
    }

    public void triggerScanNotSubmittedBugTrackerEvent(ScanRequest scanRequest, ScanResults scanResults) {

        boolean eventsWereTriggered = true;
        String description = "Existing scan in progress. Please try again after sometime.";

        BugTracker.Type bugTrackerType = scanRequest.getBugTracker().getType();

        switch (bugTrackerType) {
            case GITLABMERGE:
                gitLabService.sendMergeComment(scanRequest, SCAN_NOT_SUBMITTED_MESSAGE);
                gitLabService.endBlockMerge(scanRequest);
                break;

            case GITLABCOMMIT:
                gitLabService.sendCommitComment(scanRequest, SCAN_NOT_SUBMITTED_MESSAGE);
                break;

            case GITHUBPULL:
                gitService.sendMergeComment(scanRequest, SCAN_NOT_SUBMITTED_MESSAGE);
                String targetURL = cxPropertiesBase.getBaseUrl().concat(GitHubService.CX_USER_SCAN_QUEUE);
                gitService.errorBlockMerge(scanRequest, targetURL, description);
                break;

            case BITBUCKETPULL:
                bbService.sendMergeComment(scanRequest, SCAN_NOT_SUBMITTED_MESSAGE);
                break;

            case BITBUCKETSERVERPULL:
                bbService.sendServerMergeComment(scanRequest, SCAN_NOT_SUBMITTED_MESSAGE);
                String buildName = "Existing Checkmarx Scan in progress.";
                String buildUrl = cxPropertiesBase.getBaseUrl().concat(BitBucketService.CX_USER_SCAN_QUEUE);
                bbService.setBuildFailedStatus(scanRequest, buildName, buildUrl, description);
                break;

            case ADOPULL:
                adoService.sendMergeComment(scanRequest, SCAN_NOT_SUBMITTED_MESSAGE);
                adoService.endBlockMerge(scanRequest, scanResults, new ScanDetails());
                break;

            case JIRA:
            case CUSTOM:
                eventsWereTriggered = false;
                break; // No action is needed

            default:
                eventsWereTriggered = false;
                log.warn("Bug-Tracker type: {} is not supported", bugTrackerType);
        }

        if (eventsWereTriggered) {
            log.debug("Completed triggering events for the '{}' bug tracker.", bugTrackerType);
        }
        else {
            log.debug("Bug tracker events were not triggered, because bug tracker type is '{}'.", bugTrackerType);
        }

    }
}