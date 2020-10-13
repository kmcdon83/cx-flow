# Pre-Requisites / Requirements

  

| Software       | Version                                            | Notes                                                                |
|----------------|----------------------------------------------------|----------------------------------------------------------------------|
| Java Runtime   | 8, 11                                              | Specific builds exist for  both Java 8 and Java 11                   |
| Checkmarx SAST | 8.8/8.9, 9.0                                       | CxFlow uses Checkmarx REST APIs, which are available from 8.8 onward |
| Jira           | 6.4, 7.x, 8                                        | Jira Cloud and Software have been tested                             |
| GitHub         | Cloud and Enterprise supported versions            | Both WebHook and Issue integration                                   |
| GitLab         | Cloud, Community and Enterprise supported versions | Both WebHook and Issue integration                                   |
| Bitbucket      | Cloud, Server (5.x and greater)                    | WebHook Integration only (no issues)                                 |
| Azure DevOps   | Cloud, Server 2019, TFS Server 2018                | Booth WebHook and WorkItem integration                               |

## Additional Requirements

-   CxFlow can run anywhere with Java 1.8/11+ Runtime available
-   Sizing of server should be discussed based on the overall use case
    -   Minimum 2 core, 4GB RAM, 20 GB Disk
-   CI/CD/Execution toolset must allow for incorporating custom
    application execution (jar/cli, docker) - For CLI execution: i.e.
    Jenkins, Bamboo, GitLab CI, Drone, CircleCI, TravisCI, etc
-   Network Architecture must allow for http/s access to Checkmarx and
    defect management system if applicable.  
    -   Network architecture must allow for connectivity from repository
        to the CxFlow web service
-   Internal CA root, intermediate and self-signed certificates must be
    installed in the Java JRE truststore (cacerts) - this is applicable
    to any integration components to ensure there are no trust issues
-   Self Signed Certificates must be explicitly trusted by installation
    to the Java JRE trustsore (cacerts) 

<!-- -->

-   To enable automated scanning orchestration and project creation
    (WebHook Web Service integration) the following must be met
    -   Source Repository must be capable of supporting WebHooks,
        specifically
        -   GitLab
        -   Bitbucket Server/Cloud
        -   GitHub
        -   Azure DevOps
        -   TFS
-   Service account credentials / API tokens must be provisioned and
    made available with appropriate access to tools and services related
    to Defect Management, CI/CD tools, Source Repositories and Checkmarx

NOTE:  Careful consideration must be taken when deciding when to trigger
scans with WebHooks and similarly when to publish defects.  

  

### **GitHub Personal Access Token**

-   Create a token by clicking your profile in upper right
    corner \>settings

    -   Click Developer settings\>Personal Access Tokens\>Generate New
        Token

    -   Give the token a note/name, repo:status and public\_repo under
        the repo section

<img src="attachments/1276019198/1610187021.png?height=250" height="250" />

### **Azure DevOps Access Token**

The Azure Access Token that needs to be configured with CxFlow needs to
have the following requirements for both Push and Pull Request events:

-   Code (Read & write)

-   Work Items (Read, write, & manage)

<img src="attachments/1276019198/1615659080.png?height=400" height="400" />

Tokens only have a life cycle of 365 days maximum so having a secret
rotation cycle in place is very important in the long term.

  

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[image2020-3-17\_8-29-34.png](attachments/1276019198/1610187021.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[image2020-3-18\_10-29-42.png](attachments/1276019198/1614086517.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[AccessToken.PNG](attachments/1276019198/1615659080.png) (image/png)  
