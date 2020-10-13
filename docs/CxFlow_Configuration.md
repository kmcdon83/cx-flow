# CxFlow Configuration

-   [Monitoring](#CxFlowConfiguration-Monitoring)
-   [Encryption](#CxFlowConfiguration-Encryption)
-   [External Scripting](#CxFlowConfiguration-ExternalScripting)
-   [Main (Global)
    Properties](#CxFlowConfiguration-Main(Global)Properties)
-   [Configuration
    Definitions](#CxFlowConfiguration-ConfigurationDefinitions)
    -   [9.0 Configuration
        Changes](#CxFlowConfiguration-9.0ConfigurationChanges)
    -   [Filtering](#CxFlowConfiguration-Filtering)
    -   [Break build](#CxFlowConfiguration-Breakbuild)
-   [WebHook Configuration](#CxFlowConfiguration-WebHookConfiguration)
    -   [WebHook URL Parameters -
        Code](#CxFlowConfiguration-WebHookURLParameters-Code)
    -   [WebHook URL Override Parameters - Details (related to
        above)](#CxFlowConfiguration-WebHookURLOverrideParameters-Details(relatedtoabove))
-   [Repository configuration
    blocks](#CxFlowConfiguration-Repositoryconfigurationblocks)
    -   [GitHub](#CxFlowConfiguration-GitHub)
    -   [GitLab](#CxFlowConfiguration-GitLab)
    -   [Azure DevOps](#CxFlowConfiguration-AzureDevOps)
    -   [Bitbucket (Cloud and
        Server)](#CxFlowConfiguration-Bitbucket(CloudandServer))
-   [JSON Config Override](#CxFlowConfiguration-JSONConfigOverride)
-   [BugTrackers](#CxFlowConfiguration-BugTrackers)

CxFlow uses **SpringBoot** and requires the use of an application.yml
file to drive the execution. Below outline the applicable properties
available and when/how they can be used in different execution modes. 
Additionally, any of the SpringBoot configuration rules apply.

<https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html>

Most notably, overrides on the command line using --propery.name=Value
format as well as PROPERTY\_NAME environment variable overrides.  This
allows for bootstrapping the launch process with various configurations,
particularly with containers.

  

All of the appropriate configuration will be determined by the
**application.yml** file that resides in the same directory as the jar
file, or if an explicit configuration override is provided on the
command line as follows:

<table>
<tbody>
<tr class="odd">
<td style="text-align: left;"><div class="content-wrapper">
<div class="container" style="text-align: left;" title="Hint: double-click to select code">
<div class="line number1 index0 alt2" style="text-align: left;">
<div class="code panel pdl" style="border-width: 1px;">
<div class="codeContent panelContent pdl">
<div class="sourceCode" id="cb1" data-syntaxhighlighter-params="brush: java; gutter: false; theme: Confluence" data-theme="Confluence" style="brush: java; gutter: false; theme: Confluence"><pre class="sourceCode java"><code class="sourceCode java"><span id="cb1-1"><a href="#cb1-1"></a>java -jar cx-flow-&lt;ver&gt;.<span class="fu">jar</span> --spring.<span class="fu">config</span>.<span class="fu">location</span>=/path/to/application.<span class="fu">yml</span></span></code></pre></div>
</div>
</div>
</div>
</div>
</div></td>
</tr>
</tbody>
</table>

## Monitoring

CxFlow is built with Actuator enabled in for monitoring purposes:

<https://docs.spring.io/spring-boot/docs/2.2.0.M2/reference/html/production-ready-features.html>

## Encryption

CxFlow is bundled with support for Jasypt for encrypting property files

<http://www.jasypt.org/>

Sample:

<https://www.ricston.com/blog/encrypting-properties-in-spring-boot-with-jasypt-spring-boot/>

``` java
 java -jar cx-flow-<ver>.jar --jasypt.encryptor.password=passphrase --jasypt.encryptor.algorithm=PBEWITHHMACSHA512ANDAES_256
```

**Note: Choose the algorithm appropriate for your deployment - this is a
sample.**

**Environment variables can be leveraged for injecting the required
jasypt cli values specified above:**

JASYPT\_ENCRYPTOR.PASSWORD=passphrase

JASYPT\_ENCRYPTOR.ALGORITHM=PBEWITHHMACSHA512ANDAES\_256

  

**  
**

## External Scripting

There are places that a custom **groovy** script can be used within the
execution of CxFlow.  These include:

-   Deciding which branch is applicable for scanning (Sample script
    attached to this page)
-   The project name to be used
-   The team to be used

See more details below in the configuration options.

  

## Main (Global) Properties

**Application Properties Sample**

``` java
server:
  port: ${PORT:8080}

logging:
  file: flow.log

cx-flow:
  contact: admin@cx.com
  bug-tracker: Json
  bug-tracker-impl:
    - CxXml
    - Csv
    - Json
    - GitLab
    - GitHub
    - Azure
    - Rally
  branches:
    - develop
    - master
    - release-\w+ # regular expressions supported. If branch-script is provided, this is ignored.
  branch-script: D:\\tmp\Branch.groovy #default empty/not used
  filter-severity:
    - High
  filter-category:
    - Stored_XSS
    - SQL_Injection
  filter-cwe:
    - 89
    - 79
  filter-status:
    - New
    - Confirmed
  mitre-url: https://cwe.mitre.org/data/definitions/%s.html
  wiki-url: https://checkmarx.atlassian.net/wiki/spaces/AS/pages/79462432/Remediation+Guidance
  codebash-url: https://cxa.codebashing.com/courses/
  track-application-only: false
  web-hook-queue: 20
  scan-result-queue: 8
  break-build: false
  http-connection-timeout: xxx #milliseconds - default 30000
  http-read-timeout: xxx #milliseconds - default 120000
  mail:
    host: smtp.gmail.com
    port: 587
    username: xxx
    password: xxx
    enabled: true

checkmarx:
  username: xxx
  password: xxx
  client-secret: xxx
  base-url: https://cx.aws.checkmarx.com
  multi-tenant: true
  scan-preset: Checkmarx Default
  configuration: Default Configuration
  team: \CxServer\SP\Machina
  scan-timeout: 120 #Webhook and --scan command line only, number of minutes
  jira-project-field: jira-project
  jira-issuetype-field: jira-issuetype
  jira-custom-field: jira-fields
  jira-assignee-field: jira-assignee
  preserve-xml: true
  url: ${checkmarx.base-url}/cxrestapi
#WSDL Config
  portal-url: ${checkmarx.base-url}/cxwebinterface/Portal/CxWebService.asmx
  sdk-url: ${checkmarx.base-url}/cxwebinterface/SDK/CxSDKWebService.asmx
  portal-wsdl: ${checkmarx.base-url}/Portal/CxWebService.asmx?wsdl
  sdk-wsdl: ${checkmarx.base-url}/SDK/CxSDKWebService.asmx?wsdl
  project-script: D:\\tmp\CxProject.groovy #default empty/not used
  team-script: D:\\tmp\CxTeam.groovy #default empty/not used

github:
  webhook-token: XXXXX
  token: XXXXX
  url: https://github.com
  api-url: https://api.github.com/repos/
  false-positive-label: false-positive
  block-merge: true
  cx-summary-header: Checkmarx Scan Summary
  cx-summary: true #default false if not provided
  flow-summary-header: Violation Summary
  flow-summary: true #default true if not provided
  detailed-header: Details
  detailed: true #default true if not provided

gitlab:
  webhook-token: XXXXX
  token: XXXXX
  url: https://gitlab.com
  api-url: https://gitlab.com/api/v4/
  false-positive-label: false-positive
  block-merge: true

bitbucket:
  webhook-token: XXXXX
  token: XXXXX
  url: https://api.bitbucket.org
  api-path: /2.0
  false-positive-label: false-positive

azure:
  webhook-token: xxxx
  token: xxxx
  url: https://dev.azure.com
  issue-type: issue
  api-version: 5.0
  false-positive-label: false-positive
  #block-merge: true

rally:
  token: xxxx
  rally-project-id: xxxx
  rally-workspace-id: xxxx
  url: https://rallydev.com
  api-url: https://rally1.rallydev.com/slm/webservice/v2.0

jira:
  url: https://xxxx.atlassian.net
  username: XXXXX
  token: XXXXX
  project: <JIRA PROJECT>
  issue-type: <JIRA ISSUE TYPE>
  priorities:
    Critical: Highest
    High: High
    Medium: Medium
    Low: Low
    informational: Lowest
  open-transition: In Review
  close-transition: Done
  open-status:
    - To Do
    - In Progress
    - In Review
  closed-status:
    - Done
  fields:
    - type: result
      name: application
      jira-field-name: Application
      jira-field-type: label
    - type: result
      name: cve
      jira-field-name: CVEs
      jira-field-type: label
    - type: result
      name: cwe
      jira-field-name: CWEs
      jira-field-type: label
    - type: result
      name: category
      jira-field-name: Category
      jira-field-type: label
    - type: result
      name: loc
      jira-field-name: LOC
      jira-field-type: label
      jira-default-value: XXXXX

json:
  file-name-format: "[NAMESPACE]-[REPO]-[BRANCH]-[TIME].json"
  data-folder: "C:\\tmp"

cx-xml:
  file-name-format: "[NAMESPACE]-[REPO]-[BRANCH]-[TIME].xml"
  data-folder: "C:\\tmp"

csv:
  file-name-format: "[TEAM]-[PROJECT]-[TIME].csv"
  data-folder: "C:\\tmp"
  include-header: true
  fields:
    - header: Customer field (Application)
      name: application
      default-value: unknown
    - header: Primary URL
      name: static
      default-value: ${tmp.url}
    - header: severity
      name: severity
    - header: Vulnerability ID
      name: summary
      prefix: "[APP]:"
    - header: file
      name: filename
    - header: Vulnerability ID
      name: summary
    - header: Vulnerability Name
      name: category
    - header: Category ID
      name: cwe
    - header: Description
      name: summary
      prefix: "*"
      postfix: "*"
    - header: Severity
      name: severity
    - header: recommendation
      name: recommendation
```

  

## Configuration Definitions

Refer to the sample config above for the entire yaml structure.

<table style="width:100%;">
<colgroup>
<col style="width: 15%" />
<col style="width: 12%" />
<col style="width: 10%" />
<col style="width: 6%" />
<col style="width: 6%" />
<col style="width: 48%" />
</colgroup>
<thead>
<tr class="header">
<th>Config</th>
<th>Default</th>
<th>Required</th>
<th>WebHook</th>
<th>Command Line</th>
<th>Notes</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>server</td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
</tr>
<tr class="even">
<td>port</td>
<td>8080</td>
<td>No</td>
<td>Yes</td>
<td>No</td>
<td>Default value will be 8080 unless environment variable PORT is defined</td>
</tr>
<tr class="odd">
<td><strong>cx-flow</strong></td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
</tr>
<tr class="even">
<td>contact</td>
<td><br />
</td>
<td>No</td>
<td><br />
</td>
<td><br />
</td>
<td>Contact email for the CxFlow administrator</td>
</tr>
<tr class="odd">
<td>bug-tracker</td>
<td><br />
</td>
<td>Yes</td>
<td>Yes</td>
<td>Yes</td>
<td><p>The default feedback option.  Must be one of the following</p>
<ul>
<li>NONE</li>
<li>JIRA</li>
<li>EMAIL</li>
<li>Any value specified under the bug-tracker-impl custom bean implementations (this is a white list of bug tracker implementations)</li>
</ul>
<p>Note:  JIRA/EMAIL/NONE are built in and are not required under bug-tracker-impl list</p></td>
</tr>
<tr class="even">
<td>bug-tracker-impl</td>
<td><br />
</td>
<td>No (Only if using one of the applicable bug tracker implementations</td>
<td>Yes</td>
<td>Yes</td>
<td><p>List of available bug trackers (feedback channels).  Currently support for:</p>
<ul>
<li>Csv</li>
<li>Json</li>
<li>CxXML </li>
<li>GitLab</li>
<li>GitHub</li>
<li>Azure</li>
<li>Rally</li>
</ul></td>
</tr>
<tr class="odd">
<td>branches</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>No</td>
<td><p>List of protected branches that drive scanning within the WebHook flow.  If a pull or push event is initiated to one of the protected branches listed here, the scan will be initiated.  For example</p>
<ul>
<li>develop</li>
<li>master</li>
<li>security</li>
<li>release-\w+</li>
</ul>
<p>If no value is provided, all branches are applicable</p>
<p>Regular expressions are supported. (i.e. release-\w+ will match any branches starting with release-)</p></td>
</tr>
<tr class="even">
<td>branch-script</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>No</td>
<td><p>A <strong>groovy</strong> script that can be used for deciding if a branch is applicable for scanning.  This is to allow for any client custom lookups and other integrations.  The script is passed a "<strong>request</strong>" object, which is of type <strong>com.checkmarx.flow.dto.ScanRequest</strong>, and must return <strong>boolean</strong> (true/false).</p>
<p>If this script is provided, it will be used for all decisions associated with determining applicability for a branch event to be scanned.</p>
<p>**Sample script attached to this page.</p></td>
</tr>
<tr class="odd">
<td>filter-severity</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>The severity should be filtered during feedback (High, Medium, Low, Informational).  If no value is provided, all severity are applicable.</td>
</tr>
<tr class="even">
<td>filter-category</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>The list of vulnerability types, as defined within Checkmarx, that should be included in the results (Stored_XSS, SQL_Injection).  If no value is provided, all categories are applicable.</td>
</tr>
<tr class="odd">
<td>filter-cwe</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>The list of CWEs that should be included in the results (79, 89).  If no value is provided, all categories are applicable.</td>
</tr>
<tr class="even">
<td>filter-status</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>Only options are Urgent and Confirmed.  This allows for filtering only the results that have been confirmed/validated within  Checkmarx.</td>
</tr>
<tr class="odd">
<td>mitre-url</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>If provided, it will provide a link in the issue body for Jira, GitLab Issues, GitHub Issues to help guide developers.  The link will not be provided if empty or omitted.</td>
</tr>
<tr class="even">
<td>wiki-url</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>If provided, it will provide a link in the issue body for Jira, GitLab Issues, GitHub Issues associated with internal program references (program/assessment methodology, remediation guidance, etc).  The link will not be provided if empty or omitted.</td>
</tr>
<tr class="odd">
<td>codebash-url</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>If provided, it will provide a link in the issue body for Jira, GitLab Issues, GitHub Issues associated with training (link is titled training).  The link will not be provided if empty or omitted.</td>
</tr>
<tr class="even">
<td>track-application-only</td>
<td>false</td>
<td>No*</td>
<td>Yes</td>
<td>Yes</td>
<td><br />
</td>
</tr>
<tr class="odd">
<td>web-hook-queue</td>
<td>100</td>
<td>No*</td>
<td>Yes</td>
<td>No</td>
<td>The maximum number of active scans initiated through webhook at a given time.  Requests remain queued until a slot is free.</td>
</tr>
<tr class="even">
<td>scan-result-queue</td>
<td>4</td>
<td>No*</td>
<td>Yes</td>
<td>Yes</td>
<td>The maximum number of scan results that will be processed at the same time.  Requests remain queued until a slot is free.  As XML files can become large, it is important to limit the number that can be processed at once.</td>
</tr>
<tr class="odd">
<td>break-build</td>
<td>false</td>
<td>No*</td>
<td>No</td>
<td>Yes</td>
<td>A non zero return code (10) is given when any of the filtering criteria is met within scan results.</td>
</tr>
<tr class="even">
<td><pre><code>http-connection-timeout</code></pre></td>
<td>30000</td>
<td>No*</td>
<td>Yes</td>
<td>Yes</td>
<td>Http client connection timeout setting.  Not applied to Jira client.</td>
</tr>
<tr class="odd">
<td><pre><code>http-read-timeout</code></pre></td>
<td>120000</td>
<td>No*</td>
<td>Yes</td>
<td>Yes</td>
<td>Http client read timeout setting.  Not applied to Jira client.</td>
</tr>
<tr class="even">
<td>mail</td>
<td>enabled: false</td>
<td>No*</td>
<td>Yes</td>
<td>Yes</td>
<td><p>SMTP config - host, port, username, password, enabled (false by default).  When enabled, email is a valid feedback channel, and an html template is used to provide result details.</p>
<p>During webhook execution, the email is sent to the list of committers in the push event.</p></td>
</tr>
<tr class="odd">
<td>auto-profile</td>
<td>false</td>
<td>No</td>
<td>Yes</td>
<td>No</td>
<td>During webhook execution, language stats and files are gathered to help determine an appropriate preset to use.  By default, the profiling will only take place initially when a project is new/first created.  See <a href="CxFlow_Automated_Code_Profiling">CxFlow Automated Code Profiling</a> for details</td>
</tr>
<tr class="even">
<td>always-profile</td>
<td>false</td>
<td>No</td>
<td>Yes</td>
<td>No</td>
<td>This will enforce the auto-profile execution for each scan request regardless of whether the project is new or not</td>
</tr>
<tr class="odd">
<td>profiling-depth</td>
<td>1</td>
<td>No</td>
<td>Yes</td>
<td>No</td>
<td>The folder depth that is inspected for file names during the profiling process (i.e. looking for specific file references i.e. web.xml/Web.config)</td>
</tr>
<tr class="even">
<td>profile-config</td>
<td>CxProfile.json</td>
<td>No</td>
<td>Yes</td>
<td>No</td>
<td>The file that contains the profile configuration mapping.</td>
</tr>
<tr class="odd">
<td><strong>checkmarx</strong></td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
<td><br />
</td>
</tr>
<tr class="even">
<td>username</td>
<td><br />
</td>
<td>Yes</td>
<td>Yes</td>
<td>Yes</td>
<td>Service Account for Checkmarx</td>
</tr>
<tr class="odd">
<td>password</td>
<td><br />
</td>
<td>Yes</td>
<td>Yes</td>
<td>Yes</td>
<td>Service Account Password Checkmarx</td>
</tr>
<tr class="even">
<td>client-secret</td>
<td><br />
</td>
<td>Yes</td>
<td>Yes</td>
<td>Yes</td>
<td>OIDC client secret for API login to Checkmarx</td>
</tr>
<tr class="odd">
<td>base-url</td>
<td><br />
</td>
<td>Yes</td>
<td>Yes</td>
<td>Yes</td>
<td>Base FQDN and Port for Checkmarx</td>
</tr>
<tr class="even">
<td>multi-tenant</td>
<td>false</td>
<td>No*</td>
<td>Yes</td>
<td>Yes (Scan only)</td>
<td>If yes, the namespace will be created (or reused if pre-registered or already created from previous scans)</td>
</tr>
<tr class="odd">
<td>scan-preset</td>
<td>Checkmarx Default</td>
<td>No*</td>
<td>Yes</td>
<td>Yes (Scan only)</td>
<td>The default preset used for the triggered scans</td>
</tr>
<tr class="even">
<td>configuration</td>
<td>Default Configuration</td>
<td>No*</td>
<td>Yes</td>
<td>Yes (Scan only)</td>
<td>Checkmarx Scan Configuration Setting </td>
</tr>
<tr class="odd">
<td>team</td>
<td><br />
</td>
<td>Yes (not for XML parse mode)</td>
<td>Yes</td>
<td>Yes (Scan only)</td>
<td>Base team in Checkmarx to drive scanning and retrieving of results</td>
</tr>
<tr class="even">
<td>scan-timeout</td>
<td>120</td>
<td>No*</td>
<td>Yes</td>
<td>Yes (Scan only)</td>
<td>The amount of time in minutes that CxFlow will wait for a scan to complete to process the results.  The Checkmarx scan will be left as is, but simply no feedback will be given</td>
</tr>
<tr class="odd">
<td>jira-project-field</td>
<td>jira-project</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>Custom Checkmarx field name to override Jira Project setting for a given Checkmarx scan result / project</td>
</tr>
<tr class="even">
<td>jira-issuetype-field</td>
<td>jira-issuetype</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>Custom Checkmarx field name to override Jira Issue Type setting for a given Checkmarx scan result / project</td>
</tr>
<tr class="odd">
<td>jira-custom-field</td>
<td>jira-fields</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>Custom Checkmarx field name to override Jira custom field mappings for a given Checkmarx scan result / project</td>
</tr>
<tr class="even">
<td>jira-assignee-field</td>
<td>jira-assignee</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td>Custom Checkmarx field name to override Jira assignee for a given Checkmarx scan result / project</td>
</tr>
<tr class="odd">
<td>preserve-xml</td>
<td>false</td>
<td>No*</td>
<td>Yes</td>
<td>Yes</td>
<td>This flag is used to preserve the original XML results from Checkmarx scan inside the ScanResults object to be later used by a Custom bug tracker implementation if required.  Currently CxXMLIssueTracker uses this.</td>
</tr>
<tr class="even">
<td>incremental</td>
<td>false</td>
<td>No*</td>
<td>Yes</td>
<td>Yes</td>
<td>Enables support for Incremental scan support when CxFlow is triggering scans.  The incremental-num-scans and incremental-threshold values must not be exceeded for the last available full scan criteria.</td>
</tr>
<tr class="odd">
<td>incremental-num-scans</td>
<td>5</td>
<td>No*</td>
<td>Yes</td>
<td>Yes (Scan only)</td>
<td>The maximum number of scans before a full scan is required.</td>
</tr>
<tr class="even">
<td>project-script</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td><p>A <strong>groovy</strong> script that can be used for deciding the name of the project to create/use in Checkmarx.  This is to allow for any client custom lookups and other integrations.  The script is passed a "<strong>request</strong>" object, which is of type <strong>com.checkmarx.flow.dto.ScanRequest</strong>, and must return <strong>String</strong> representing the <strong>team name</strong> to be used.</p>
<p>If this script is provided, it will be used for all decisions associated with determining project name.</p></td>
</tr>
<tr class="odd">
<td>team-script</td>
<td><br />
</td>
<td>No</td>
<td>Yes</td>
<td>Yes</td>
<td><p>A <strong>groovy</strong> script that can be used for deciding the the team to use in Checkmarx.  This is to allow for any client custom lookups and other integrations.  The script is passed a "<strong>request</strong>" object, which is of type <strong>com.checkmarx.flow.dto.ScanRequest</strong>, and must return <strong>String</strong> representing the <strong>team path</strong> to be used.</p>
<p>If this script is provided, it will be used for all decisions associated with determining project name.</p></td>
</tr>
<tr class="even">
<td>incremental-threshold</td>
<td>7</td>
<td>No*</td>
<td>Yes</td>
<td>Yes (Scan only)</td>
<td>The maximum number of days before a full scan is required</td>
</tr>
<tr class="odd">
<td>offline</td>
<td>false</td>
<td>No*</td>
<td>No</td>
<td>Yes (Parse only)</td>
<td>Use <a href="https://checkmarx.atlassian.net/wiki/spaces/PTS/pages/edit/1276641334?draftId=1277231124&amp;draftShareId=3b5bb407-80d5-41dd-8423-0b7d8845007e&amp;" class="toolbar-trigger aui-dd-trigger aui-button">Table</a> d only when parsing Checkmarx XML, this flag will removing the dependency from Checkmarx APIs when parsing results.  This will skip retrieval of the issue description from Checkmarx.</td>
</tr>
</tbody>
</table>

*No\* → Default is applied*

### 9.0 Configuration Changes

``` java
checkmarx:
  version: 9.0
  username: xxxxx
  password: xxxxx
  client-id: resource_owner_client
  client-secret: 014DF517-39D1-4453-B7B3-9930C563627C
  scope: access_control_api sast_rest_api
  base-url: http://cx.local
  multi-tenant: true
  configuration: Default Configuration
  scan-preset: Checkmarx Default
  team: /CxServer/Checkmarx/CxFlow
  url: ${checkmarx.base-url}/cxrestapi
  preserve-xml: true
  incremental: true
  #WSDL Config
  portal-url: ${checkmarx.base-url}/cxwebinterface/Portal/CxWebService.asmx
  #project-script: D:\\tmp\CxProject.groovy
  #team-script: D:\\tmp\CxTeam.groovy
  #exclude-files:
  #exclude-folders:
```

It is important to include **version: *9.0*** (or higher), and
**scope:** *** access\_control\_api sast\_rest\_api***

***Team Path: **Must include unix path separator /*

  

### Filtering

Filtering, as specified above, is available on the following criteria:

-   Severity → Severity from Checkmarx
-   Category → Vulnerability name within Checkmarx
-   CWE → CWE value from Checkmarx
-   Status → Urgent \| Confirmed

All values are case sensitive as per the output from Checkmarx (i.e.
**H**igh severity, **S**tored\_**XSS**, **C**onfirmed)

### Break build

It is worth noting that the configuration can be set, or overridden at
execution time on the command line (**--cx-flow.break-build=true**) to
exit the command line execution flow for a single project result or scan
that has results that meet the filter criteria.

Note:  This is not applicable for WebHooks or for batch (instance and
team) cli execution.  It will only work in the event one project result
is being processed.

## WebHook Configuration

Each repository type will require its own specific configuration block
as defined below.  Each of these have available overrides that can be
given in the form of URL parameters or as a JSON configuration blob that
is base64 encoded and provided as a url parameter (override=\<XXXXXX\>).

WebHook scans are triggered based on the protected branches list. This
configuration is under the config block

For **Pull/Merge** - if a request is made to pull/merge code into one of
the listed protected branches, then CxFlow will trigger the scan.  The
pull/merge will be commented with the filtered findings from Checkmarx. 

For **Push** the findings will be published according to the specified
**bug-tracker** in the main (or overridden configuration) - i.e.
JSON/CSV/XML output or Jira defect.

Please refer to the workflow for [WebHooks](Cx_Flow_Workflows).

### WebHook URL Parameters - Code

``` java
            @RequestParam(value = "application", required = false) String application,
            @RequestParam(value = "branch", required = false) List<String> branch,
            @RequestParam(value = "severity", required = false) List<String> severity,
            @RequestParam(value = "cwe", required = false) List<String> cwe,
            @RequestParam(value = "category", required = false) List<String> category,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "team", required = false) String team,
            @RequestParam(value = "status", required = false) List<String> status,
            @RequestParam(value = "assignee", required = false) String assignee,
            @RequestParam(value = "preset", required = false) String preset,
            @RequestParam(value = "incremental", required = false) Boolean incremental,
            @RequestParam(value = "exclude-files", required = false) List<String> excludeFiles,
            @RequestParam(value = "exclude-folders", required = false) List<String> excludeFolders,
            @RequestParam(value = "override", required = false) String override,
            @RequestParam(value = "bug", required = false) String bug,
            @RequestParam(value = "app-only", required = false) Boolean appOnlyTracking
```

### WebHook URL Override Parameters - Details (related to above)

| Configuration   | Description                                                                                                                                                                                                                                                             |
|-----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| application     | Override the application name, which is directly linked to Jira and other defect management implementations for tracking purposes.                                                                                                                                      |
| branch          | Override the protected branches that will drive the scan.  For multiple branches simply list branch multiple times.  i.e. branch=XXX&branch=YYYY                                                                                                                        |
| severity        | Override the severity filters.  For multiple severity simply list multiple times.  i.e. severity=High&severity=Medium                                                                                                                                                   |
| cwe             | Override the cwe filters.  For multiple cwe simply list multiple times.  i.e. cwe=89&cwe=79                                                                                                                                                                             |
| category        | Override the category filters.  For multiple category simply list multiple times.  i.e. category=Stored\_XSS&category=SQL\_Injection                                                                                                                                    |
| project         | Override the project name that will be created/used in Checkmarx.  This allows for greater flexibility for incremental scan relating to pull requests.  I.e. Use a standardized pull project name that is always used regardless of the branch - **?project=repo-pull** |
| team            | Override the team within Checkmarx to use/create project under.                                                                                                                                                                                                         |
| status          | Override the status filters (Confirmed/Urgent)                                                                                                                                                                                                                          |
| assignee        | Override the assignee (Jira)                                                                                                                                                                                                                                            |
| preset          | Override the Checkmarx preset rules for scanning                                                                                                                                                                                                                        |
| incremental     | Override incremental property to enable/disable incremental scan support                                                                                                                                                                                                |
| exclude-files   | Override file exclusions                                                                                                                                                                                                                                                |
| exclude-folders | Override folder exclusions                                                                                                                                                                                                                                              |
| override        | Override a complete **JSON blob** as defined below                                                                                                                                                                                                                      |
| bug             | Override the default configured bug                                                                                                                                                                                                                                     |
| app-only        | This forces Jira issues to be tracked according to the defined application / repo name, as opposed to defining uniqueness per namespace/repo/branch                                                                                                                     |

*Note:  No overrides are required.  It is only in the event you would
like to override the global configuration specified from the main
application.yml*

## Repository configuration blocks

  

Each of the Repository configurations have common elements such as:

-   token → api token for the repo, to gain access (typically personal
    access token for a service account)
-   web-token → CxFlow shared secret to be used when registering the
    webhook on the repo
-   url → base url for the repo
-   api-url → base url for the api endpoints for the repo
-   block-merge → boolean, determine if merge should be blocked while
    scan is completing in Checkmarx
-   cx-summary-header → Pull/Merge Markdown comment header for the
    Checkmarx Summary, if used
-   cx-summary → boolean, determine if the base Checkmarx Summary is
    displayed (unfiltered)
-   flow-summary-header → Pull/Merge Markdown comment header for the
    CxFlow Violation Summary, if used
-   flow-summary →  boolean, determine if the base CxFlow Violation
    Summary is displayed (filtered)
-   detailed-header → Pull/Merge Markdown comment header for the CxFlow
    details, if used
-   detailed →  boolean, determine if the detailed CxFlow results
    (vulnerability lines/files are displayed 

### GitHub

``` java
github:
    webhook-token: xxxx
    token: xxxx
    url: https://github.com
    api-url: https://api.github.com/repos/
    false-positive-label: false-positive
    block-merge: true
    detailed: false
    
```

<table style="width:100%;">
<colgroup>
<col style="width: 18%" />
<col style="width: 8%" />
<col style="width: 72%" />
</colgroup>
<thead>
<tr class="header">
<th>Configuration</th>
<th>Default</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>webhook-token</td>
<td><br />
</td>
<td>Token used as a shared secret when calling the CxFlow WebHook WebService.  It will authenticate the request.  GitHub will sign the request with this value, and signature is validated on the receiving end.</td>
</tr>
<tr class="even">
<td>token</td>
<td><br />
</td>
<td>This is the API token with access to the repository, with at least Read only access to code, the ability to add comments to pull requests and the ability to create GitHub Issues.</td>
</tr>
<tr class="odd">
<td>url</td>
<td><br />
</td>
<td>Main repo url for GitHub.</td>
</tr>
<tr class="even">
<td>api-url</td>
<td><br />
</td>
<td>The API endpoint for GitHub, which is a different context or potentially FQDN than the main repo url.</td>
</tr>
<tr class="odd">
<td>false-positive-label</td>
<td>false-positive</td>
<td>A label that can be defined within the GitHub Issue feedback that is used to ignore issues</td>
</tr>
<tr class="even">
<td>block-merge</td>
<td>false</td>
<td>When triggering scans based on PullRequest, this will create a new status of pending, which will block the merge ability until the scan is complete in Checkmarx.</td>
</tr>
</tbody>
</table>

*Note:* As mentioned in the prerequisites, a service account is required
that has appropriate access to the repositories that will be scanned,
pull requests that will be commented on, GitHub issues that will be
created/updated.

### GitLab

``` java
gitlab:
  webhook-token: xxxx
  token: xxxx
  url: https://gitlab.com
  api-url: https://gitlab.com/api/v4/
  false-positive-label: false-positive
  block-merge: true
```

<table style="width:100%;">
<colgroup>
<col style="width: 18%" />
<col style="width: 8%" />
<col style="width: 72%" />
</colgroup>
<thead>
<tr class="header">
<th>Configuration</th>
<th>Default</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>webhook-token</td>
<td><br />
</td>
<td>Token used as a shared secret when calling the CxFlow WebHook WebService.  It will authenticate the request.</td>
</tr>
<tr class="even">
<td>token</td>
<td><br />
</td>
<td>This is the API token with access to the repository, with at least Read only access to code, the ability to add comments to pull requests and the ability to create GitLab Issues.</td>
</tr>
<tr class="odd">
<td>url</td>
<td><br />
</td>
<td>Main repo url for GitLab.</td>
</tr>
<tr class="even">
<td>api-url</td>
<td><br />
</td>
<td>The API endpoint for GitLab, which is a different context or potentially FQDN than the main repo url.</td>
</tr>
<tr class="odd">
<td>false-positive-label</td>
<td>false-positive</td>
<td>A label that can be defined within the GitLab Issue feedback that is used to ignore issues</td>
</tr>
<tr class="even">
<td>block-merge</td>
<td>false</td>
<td>When triggering scans based on Merge Request, this will mark the Merge request as WIP in GitLab, which will block the merge ability until the scan is complete in Checkmarx.</td>
</tr>
</tbody>
</table>

*Note:* As mentioned in the prerequisites, a service account is required
that has appropriate access to the repositories that will be scanned,
pull requests that will be commented on, GitLab issues that will be
created/updated.

### Azure DevOps

``` java
azure:
  webhook-token: cxflow:1234
  token: xxxx
  url: https://dev.azure.com/XXXXXX
  issue-type: issue
  api-version: 5.0
  false-positive-label: false-positive
  block-merge: true
  closed-status: Closed
  open-status: Active
```

<table style="width:100%;">
<colgroup>
<col style="width: 18%" />
<col style="width: 8%" />
<col style="width: 72%" />
</colgroup>
<thead>
<tr class="header">
<th>Configuration</th>
<th>Default</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>webhook-token</td>
<td><br />
</td>
<td>&lt;user&gt;:&lt;token&gt; as defined when registering the event in ADO.  Used as a shared secret when calling the CxFlow WebHook WebService.  It will authenticate the request.</td>
</tr>
<tr class="even">
<td>token</td>
<td><br />
</td>
<td>This is the API token with access to the repository, with at least Read only access to code, the ability to add comments to pull requests and the ability to create Azure WorkItems.</td>
</tr>
<tr class="odd">
<td>url</td>
<td><br />
</td>
<td>Main repo url for Azure DevOps, including high level namespace.  Note: this is only required when running from command line, and not for WebHooks.</td>
</tr>
<tr class="even">
<td>issue-type</td>
<td>issue</td>
<td>The WorkItem type within Azure. i.e. issue / impediment.</td>
</tr>
<tr class="odd">
<td>issue-body</td>
<td>Description</td>
<td>The issue details body to write content of the issue to.  Default across various workItem types are Description (or System.Description)</td>
</tr>
<tr class="even">
<td>app-tag-prefix</td>
<td>app</td>
<td>Used for tracking existing issues.  Issues are tagged with this value if app is provided (without namespace/repo/branch)</td>
</tr>
<tr class="odd">
<td>owner-tag-prefix</td>
<td>owner</td>
<td>Used for tracking existing issues.  Issues are tagged with this value.</td>
</tr>
<tr class="even">
<td>repo-tag-prefix</td>
<td>repo</td>
<td>Used for tracking existing issues.  Issues are tagged with this value.</td>
</tr>
<tr class="odd">
<td>branch-label-prefix</td>
<td>branch</td>
<td>Used for tracking existing issues.  Issues are tagged with this value.</td>
</tr>
<tr class="even">
<td>api-version</td>
<td>5.0</td>
<td>Azure DevOps API version to use</td>
</tr>
<tr class="odd">
<td>open-status</td>
<td><br />
</td>
<td>Status when re-opening a workItem</td>
</tr>
<tr class="even">
<td>closed-status</td>
<td><br />
</td>
<td>Status when closing a workItem</td>
</tr>
<tr class="odd">
<td>false-positive-label</td>
<td>false-positive</td>
<td>A label/tag that can be defined within the Azure Issue feedback that is used to ignore issues</td>
</tr>
<tr class="even">
<td>block-merge</td>
<td>false</td>
<td>When triggering scans based on Pull Request, this will mark the Pull as in a blocked state until the scan is complete in Checkmarx.</td>
</tr>
</tbody>
</table>

*Note:* As mentioned in the prerequisites, a service account is required
that has appropriate access to the repositories that will be scanned,
pull requests that will be commented on, Azure WorkItems that will be
created/updated.

### Bitbucket (Cloud and Server)

``` java
bitbucket:
  webhook-token:
  token: <user>:xxx
  url: https://api.bitbucket.org
  api-path: /2.0
```

<table style="width:100%;">
<colgroup>
<col style="width: 18%" />
<col style="width: 8%" />
<col style="width: 72%" />
</colgroup>
<thead>
<tr class="header">
<th>Configuration</th>
<th>Default</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>webhook-token</td>
<td><br />
</td>
<td>Token used as a shared secret when calling the CxFlow WebHook WebService.  It will authenticate the request.  Bitbucket cloud does not allow for a shared secret, so a url parameter called token, must be provided in this case.</td>
</tr>
<tr class="even">
<td>token</td>
<td><br />
</td>
<td>This is the API token with access to the repository, with at least Read only access to code, the ability to add comments to pull requests.  Bitbucket requires &lt;user&gt;:&lt;token&gt; format in the configuration</td>
</tr>
<tr class="odd">
<td>url</td>
<td><br />
</td>
<td><p><a href="https://api.bitbucket.org">https://api.bitbucket.org</a> (URL for Cloud Bitbucket)</p>
<p><a href="https://api.companyxyzbitbucker">https://api.companyxyzbitbucke</a>t (URL for Bitbucket server is just the server hostname with api. prefixed)</p></td>
</tr>
<tr class="even">
<td>api-path</td>
<td><br />
</td>
<td>The API url path (appended to the url) for BitBucket</td>
</tr>
</tbody>
</table>

*Note:* As mentioned in the prerequisites, a service account is required
that has appropriate access to the repositories that will be scanned,
pull requests that will be commented on, GitHub issues that will be
created/updated.

## JSON Config Override

Here is a sample override configuration in JSON format.  It has
similarities with the YAML config blocks.  Main use is to override
cx-flow and Jira yaml configuration.

``` js
{
  "application": "test app",
  "branches": ["develop", "master"],
  "incremental": true,
  "scan_preset": "Checkmarx Default",
  "exclude_folders": "tmp/",
  "exclude_files": "*.tst",
  "emails": ["xxxx@checkmarx.com"],
  "filters" : {
    "severity": ["High", "Medium"],
    "cwe": ["79", "89"],
    "category": ["XSS_Reflected", "SQL_Injection"],
    "status": ["Confirmed", "New"]
  },
  "jira": {
    "project": "APPSEC",
    "issue_type": "Bug",
    "assignee": "admin",
    "opened_status": ["Open","Reopen"],
    "closed_status": ["Closed","Done"],
    "open_transition": "Reopen Issue",
    "close_transition": "Close Issue",
    "close_transition_field": "resolution",
    "close_transition_value": "Done",
    "priorities": {
      "High": "High",
      "Medium": "High",
      "Low": "High"
    },
    "fields": [
      {
        "type": "cx", //cx, static,  result
        "name": "xxx",
        "jira_field_name": "xxxx",
        "jira_field_type": "text", //security text | label | single-select | multi-select
        "jira_default_value": "xxx"
      },
      {
        "type": "result",
        "name": "xxx",
        "jira_field_name": "xxxx",
        "jira_field_type": "label"
      },
      {
        "type": "static",
        "name": "xxx",
        "jira_field_name": "xxxx",
        "jira_field_type": "label",
        "jira_default_value": "xxx"
      }
    ]
  }
}
```

  

## BugTrackers

Currently the following BugTrackers / Feedback Channels are available

-   XML
-   JSON
-   CSV
-   GitLab
-   GitHub
-   Azure
-   Jira

Please refer to the [Bug Tracker
documentation](Bug_Trackers_Feedback_Channels) for further details.

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CheckBranch.groovy](attachments/1276641334/1660518788.groovy)
(application/octet-stream)  
