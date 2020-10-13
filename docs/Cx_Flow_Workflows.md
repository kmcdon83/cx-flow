# Cx Flow Workflows

  

  

## WebHook WebService

Running CxFlow as a web service will enable registering WebHook Push and
Pull/Merge events in GitHub, GitLab, Bitbucket (Cloud/Server) to drive
scanning automation.

  

###### **Workflow**

1.  Webhook is registered at the namespace level (aka group,
    organization) or at the individual project/repository level within
    GitLab, GitHub, or Bitbucket using a shared key/token and pointing
    to the Automation Service
2.  Developer commit's code (PUSH Request)
3.  WebHook fires a request to the Service along with commit details
4.  All developers identified within the commit(s) of a PUSH request are
    notified via email that the scan has been submitted (note: email can
    be disabled)
5.  Service determines if the branch is applicable to the service (see
    Branch details below)
6.  Service creates a new team (if multi-tenant mode is on) and a
    project for the particular organization/repository within
    Checkmarx.  If a project already exists with the same name, the
    existing project is used
7.  Project is set to use specific scanning rules (Preset)
8.  Repository details are updated in the project within Checkmarx
9.  Scan request is submitted for the project in Checkmarx
10. Service monitors the state of the scan, and waits until it is
    finished
11. Once scan is finished, a report is generated and retrieved by the
    Service
12. Findings are filtered based on defined criteria (see Filter details
    below)
13. Service sends an email notification to all committers that scan is
    complete
    1.  Email includes direct links to issues based on Filtering
14. Service publishes findings to defined Bug Tracking tool
    1.  Issues are collapsed (multiple issues of the same type in the
        same file are updated within 1 ticket) - See Bug Tracking
        details below
    2.  Tickets are closed if the issue is remediated on next iteration
        of scan
    3.  Tickets are re-opened in the event an issue is reintroduced
    4.  All references within a ticket must be addressed before the
        Ticket is closed

## Command Line

### **Parse**

Parse mode will use the Checkmarx Scan XML as input to drive the
automation

  

###### **Workflow**

1.  Existing CI/CD pipeline executes flow in Jenkins, bamboo, GitLab,
    etc - pulls code, submits code to Checkmarx (through plugin, CLI,
    SDK) and produces XML results file
2.  Automation executable ingests the XML  
3.  Findings are filtered based on defined criteria (see Filter details
    below)
4.  Automation executable publishes findings to defined defect
    management tool
    1.  Issues are collapsed (multiple issues of the same type in the
        same file are updated within 1 ticket) - See Bug Tracking
        details below
    2.  Tickets are closed if the issue is remediated on next iteration
        of scan
    3.  Tickets are re-opened in the event an issue is reintroduced
    4.  All references within a ticket must be addressed before the
        Ticket is closed

### **Source / Zip**

###### **Workflow**

1.  Existing CI/CD pipeline is executed
2.  CxFlow Service is executed with applicable parameters including path
    to the source
3.  CxFlow Service creates a new team (if multi-tenant mode is on) and a
    project for the particular organization/repository within
    Checkmarx.  If a project already exists with the same name, the
    existing project is used
4.  Project is set to use specific scanning rules (Preset)
5.  Repository details are updated in the project within Checkmarx -
    Source folder is zipped and submitted to Checkmarx
6.  Scan request is submitted for the project in Checkmarx
7.  CxFlow monitors the state of the scan, and waits until it is
    finished
8.  Once scan is finished, a report is generated and retrieved by CxFlow
    Service
9.  Findings are filtered based on defined criteria (see Filter details
    below)
10. CxFlow Service sends an email notification to all committers that
    scan is complete - only in the event 
    1.  Email includes direct links to issues based on Filtering
11. CxFlow Service publishes findings to defined Bug Tracking tool
    1.  Issues are collapsed (multiple issues of the same type in the
        same file are updated within 1 ticket) - See Bug Tracking
        details below
    2.  Tickets are closed if the issue is remediated on next iteration
        of scan
    3.  Tickets are re-opened in the event an issue is reintroduced
    4.  All references within a ticket must be addressed before the
        Ticket is closed

### **Batch**

The batch mode execution is not used to drive scanning (or parse
provided XML), but instead is used to retrieve results of the latest
scan(s) in Checkmarx and publish feedback/defects according to the
bug-tracker specified.

**By Instance**

1.  CxFlow Service executed indicating batch mode will be used
2.  CxFlow will retrieve a list of all projects on the instance of
    Checkmarx
3.  A report generation will be initiated for each project for the
    latest scan (if available)
4.  CxFlow ResultService will thread out processing each project's
    results
    1.  Each bug tracker is implementation specific
    2.  If file based (XML, CSV, JSON), a new file will be created per
        project.  The file name can be composed of Team, Project, and
        static content among other things
    3.  If Jira is used, the global Jira configuration will be used for
        each project and the ability to drive the project, issuetype,
        custom field mappings, assignee can all be overridden by custom
        fields within Checkmarx

**By Team**

Same as defined above for the Instance, except it will retrieve
projects/scan results for a given team that is provided on the command
line during execution

**By Project**

1.  A report generation will be initiated for the project for the latest
    scan 
2.  CxFlow ResultService will project results
    1.  Each bug tracker is implementation specific
    2.  If file based (XML, CSV, JSON), a new file will be created per
        project.  The file name can be composed of Team, Project, and
        static content among other things
    3.  If Jira is used, the global Jira configuration will be used for
        each project and the ability to drive the project, issuetype,
        custom field mappings, assignee can all be overridden by custom
        fields within Checkmarx

  

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CxFlow-Webhook.png](attachments/1276019228/1277722637.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CxFlow-Webhook (1).png](attachments/1276019228/1277689860.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CxFlow-Webhook (2).png](attachments/1276019228/1277886468.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CxFlow-Parse (2).png](attachments/1276019228/1277919233.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CxFlow-Source.png](attachments/1276019228/1277886473.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level](attachments/1276019228/1304002688)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level.png](attachments/1276019228/1304232052.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level](attachments/1276019228/1304363103)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level.png](attachments/1276019228/1304166527.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level](attachments/1276019228/1304723476)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level.png](attachments/1276019228/1304526874.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks.drawio](attachments/1276019228/1304199303.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks.drawio.png](attachments/1276019228/1304297664.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
XML.drawio](attachments/1276019228/1304297705.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
XML.drawio.png](attachments/1276019228/1304232071.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Results.drawio](attachments/1276019228/1304101109.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Results.drawio.png](attachments/1276019228/1304068282.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level](attachments/1276019228/1304723490)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level.png](attachments/1276019228/1304723500.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level](attachments/1276019228/1551139856)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level.png](attachments/1276019228/1551172556.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio](attachments/1276019228/1541670398.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio.png](attachments/1276019228/1541572544.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Results-min.drawio](attachments/1276019228/1305510323.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Results-min.drawio.png](attachments/1276019228/1305674176.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CICD-min](attachments/1276019228/1541572654)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CICD-min.png](attachments/1276019228/1541375575.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541539331.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541670388.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541539340.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541375536.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541572516.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541539369.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio](attachments/1276019228/1541572619.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio.png](attachments/1276019228/1541572629.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541670412.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio](attachments/1276019228/1541670465.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio.png](attachments/1276019228/1541375606.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow
WebHooks-min.drawio.tmp](attachments/1276019228/1541539280.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CICD-min.tmp](attachments/1276019228/1541670422.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CICD-min.tmp](attachments/1276019228/1541375562.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CICD-min.tmp](attachments/1276019228/1541539441.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CICD-min](attachments/1276019228/1306263568)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[CICD-min.png](attachments/1276019228/1306132514.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CICD-min.tmp](attachments/1276019228/1541375557.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio](attachments/1276019228/1305674120.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
WebHooks-min.drawio.png](attachments/1276019228/1305707079.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1551565642.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1551500282.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1551434783.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1550353241.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1551598460.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1551598469.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1551336494.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1551402031.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1550353250.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level](attachments/1276019228/1550255242)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level.png](attachments/1276019228/1550353278.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1599242872.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level](attachments/1276019228/1304297616)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Integration High-Level.png](attachments/1276019228/1304068143.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Integration High-Level.tmp](attachments/1276019228/1550320994.tmp)
(application/vnd.jgraph.mxfile)  
