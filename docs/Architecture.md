# Architecture

# Web Service (WebHook)

## Overview

-   CxFlow Listens to HTTP/S requests matching specific payloads that
    represent Pull/Merge Requests and Push Requests.
    -   Bitbucket (Server & Cloud)
    -   GitHub 
    -   Azure DevOps / TFS
    -   GitLab
-   CxFlow will be configured to process events associated with branches
    deemed important/protected across the enterprise based on
    -   List of static values
        -   master
        -   develop
        -   release
    -   List of regular expressions
    -   External Groovy Script execution hooks
-   Upon receiving an event, CxFlow will:
    -   A scan request for the repository will be initiated 
        -   Scans will be attempted as incremental with the following
            rules (optional):
            -   A full scan was conducted within the last 7 days
                (configurable)
            -   A scan was conducted within the last 5 scans
                (configurable)
        -   Global file exclusion pattern(s) will be applied for every
            scan according to the CxFlow configuration
    -   Optionally Result feedback can be configured 
        -   CxFlow will generate the XML report
        -   Results will be filtered
        -   Results will be published according to the configured
            feedback channel(s)

<!-- -->

-   -   -   File type, number of references, percentage of code base
            (reflected from post exclusions) will be mapped
        -   CxFlow will iterate through a rule set that will attempt to
            match the fingerprint of the source code
            -   Rules will be evaluated in order provided in the
                configuration file, once a match is found it will stop
                checking further
            -   There will be a default / catchall rule for those not
                matching a finger print
        -   The based on the fingerprint rule matching, an associated
            Checkmarx preset will be assigned
        -   This information will be save to the local CxFlow
            cache.  *Note:  If multiple instances of CxFlow are load
            balanced, the cache will only be available to the local
            instance doing the processing.  This design can be enhanced
            if required.*

    -   A scan request for the repository will be initiated with the
        scan preset that has been assigned in the previous step(s)
        -   Scans will be attempted as incremental with the following
            rules:
            -   A full scan was conducted within the last 7 days
            -   A scan was conducted within the last 5 scans
        -   Global file exclusion pattern(s) will be applied for every
            scan according to the CxFlow configuration

    -   Optionally Result feedback can be configured 
        -   CxFlow will generate the XML report
        -   Results will be filtered
        -   Results will be published according to the configured
            feedback channel(s)

### Load balancing

CxFlow works easily with Load Balancing as it is stateless / RESTful and
can run on **any available port**

## Workflow

Please see the [following](Cx_Flow_Workflows) for additional
information.

## Network Architecture

### Inbound

CxFlow requires network connectivity from the SCM Repository to the
specified port that is listening on (8443 - HTTPS \| 8080 - HTTP)

CxFlow can filter access based on IP(s) 

Note:  This will be available in the next release

### Outbound

Access is required from CxFlow to any desired feedback channels over
HTTP/S:

-   Jira
-   Azure DevOps WorkItems
-   GitLab Issues
-   GitHub Issues
-   Pull / Merge Request Markdown Comments

Access is required for any Checkmarx Instances

Software Components

-   Java Runtime 8 or 11
-   Gradle 5.4 (Build)
-   Spring Boot 2.1.9
    -   Spring boot is regularly updated as part of ongoing 3rd party
        library maintenance

## Execution

Please see the [following](Cx_Flow_Execution) for detailed execution
instructions

## Configuration

Please see the [following](CxFlow_Configuration) for detailed
configuration instructions

## Access Control

### Defect Trackers

Access to the various defect management systems is provided through a
service account and leverages an API token or service account
credentials)

Access should be granted to the service account appropriate to the
desired use cases (i.e. create/update/close issue)

### Checkmarx

Access to Checkmarx is granted through OIDC JWT Token in the same
fashion as any of the Checkmarx Plugins.  Required access is to Create
teams, projects, initiate scans, retrieve results.

### SCM Repository

Access to the various defect SCM Repositories (Pull Request Feedback /
Repo Issues) is provided through a service account and leverages an API
token or service account credentials)

Access should be granted to the service account appropriate to the
desired use cases (i.e. read access to all relevant source/read access
to Pull events/access to comment on Pull Requests)

### Secrets/Credentials

Credentials can be injected into CxFlow using several techniques
according to the
following: <https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/boot-features-external-config.html>

These credentials can be encrypted leveraging Jasypt - see the following

### CxFlow

SCM Repository events are authenticated through a shared key/token. 
Each SCM provides a different mechanism to authenticate the request. 
GitHub and Bitbucket Server use digital signature validation leveraging
the shared key/token.  Others use a basic auth or API token header -
this is specific and relative to the SCM design.

## Data Elements

### Inputs

-   SCM Repository Event Details associated code management events. 
    GitHub
    Example: <https://developer.github.com/v3/activity/events/types/#pullrequestevent>
-   Checkmarx Results.  As scans are finished, the results are generated
    and pulled from Checkmarx.  Checkmarx scans are triggered using GIT
    URL scan configuration with Auth token (GIT Clone).  This means tht
    GIT must be configured on CxSAST

### Outputs

-   SAST/OSA Scan results 
-   HTTP API Payload with Vulnerability details.  See
    [feedback](Bug_Trackers_Feedback_Channels) channels for details

### Persistence

CxFlow has no persistence layer.  It is stateless and be scaled easily
behind several LB and container orchestration technologies.  It instead
relies on Checkmarx and Defect management system to store defects in a
meaningful way to associate projects to existing issues/defects/

### Credentials

Credentials are stored/injected through various means as specified above
in the access control section.

## Logging

Logging details can be found here [Troubleshooting
CxFlow](Troubleshooting_CxFlow)

Logging elements contain unique identifiers to associated all events for
a specific payload/event request through to completion.  All events are
logged for inbound SCM events and outbound feedback channel (defect
management) events.

## Development

Refer the following for details

[Development Operations](Development_Operations)

[Development](https://checkmarx.atlassian.net/wiki/spaces/PTS/pages/1278116082/Development)

## Support

Refer the following for details

[CxFlow Operational Model](CxFlow_Operational_Model)

## Backlog

Issues and feature requests are managed here:

<https://github.com/checkmarx-ts/cx-flow/issues>

## Build/Release

Build and Release is managed through CircleCI using gradle.  Releases
are automated published (develop branch for TS version) to

**GitHub
Release **<https://github.com/checkmarx-ts/cx-flow/releases> (compile
JAR)

**DockerHub **<https://hub.docker.com/r/checkmarxts/cxflow>

  

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358311)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867889)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369835127)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867898)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369835136)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369802681)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358320)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358329)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358338)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358347)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369835145)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867907)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867916)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358356)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369802690)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867925)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867934)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369835154)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369835163)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358365)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358374)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867943)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Untitled Diagram.drawio](attachments/1369867881/1369835189.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369802702)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Untitled Diagram.drawio.png](attachments/1369867881/1370358505.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358403)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358412)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867964)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369867973)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358421)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1370358430)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Workflow.drawio](attachments/1369867881/1369802711.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Workflow.drawio.png](attachments/1369867881/1369802716.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[blob](attachments/1369867881/1369802675)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358442.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369867995.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369868004.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802732.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802741.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369868013.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802750.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358451.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802759.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358460.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358469.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369835180.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358478.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802768.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802777.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802786.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358487.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369868022.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358496.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1370358515.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Untitled Diagram.drawio](attachments/1369867881/1370358383.drawio)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[Untitled Diagram.drawio.png](attachments/1369867881/1369835172.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~Untitled Diagram.drawio.tmp](attachments/1369867881/1369802727.tmp)
(application/octet-stream)  
