# Cx Flow Execution

Please refer to the [configuration page](CxFlow_Configuration) for all
available options / overrides.

CxFlow either runs as a Web Service or a command line executable.  In
the event there are no arguments passed, the default behavior is to
launch the Web Service mode, otherwise the command line is invoked.  In
the event there is a need to add command line parameters while launching
the Web Service (i.e. spring.config.location) simply pass **--web **as
an option to force the WebService mode.

## WebHook

Refer to the [webhook registration](WebHook_Registration) instructions.

To launch the WebHook WebService, choose the [appropriate jar
file](Building_CxFlow_from_Source) and simply run the command:

``` java
java -jar cx-flow-<ver>.jar
```

All of the appropriate configuration will be determined by the
application.yml file that resides in the same directory as the jar file,
or if an explicit configuration override is provided on the command line
as follows:

``` java
java -jar cx-flow-<ver>.jar --spring.config.location=/path/to/application.yml --web
```

### Docker

The CxFlow docker images on Docker
Hub [checkmarxts/cxflow](https://hub.docker.com/r/checkmarxts/cxflow)
have both the latest and previous versions of CxFlow.

``` java
docker pull checkmarxts/cxflow
docker run --env-file=.checkmarx --name=cxflow --detach -p <host port>:8080 checkmarxts/cxflow
```

The env-file provides the necessary overrides during the bootstrap
process - urls, credentials, etc - sample below.

Alternatively on \*Unix hosts, you can use `--env-file <(env)` to pass
in the whole environment from the host into the Docker container.

``` java
BITBUCKET_TOKEN=<user>:<token>
BITBUCKET_URL=http://xxxxxx:7990
BITBUCKET_API_PATH=/rest/api/1.0/
BITBUCKET_WEBHOOK_TOKEN=XXXXXXX
CHECKMARX_BASE_URL=https://xxxxxxxx
CHECKMARX_CLIENT_SECRET=XXXXXXXXXX
CHECKMARX_PASSWORD=XXXXXXXX
CHECKMARX_USERNAME=XXXXXXXX
CHECKMARX_TEAM=\CxServer\SP\Checkmarx
GITHUB_TOKEN=XXXXXXXXXXXXXX
GITHUB_WEBHOOK_TOKEN=XXXXXXXX
GITLAB_TOKEN=XXXXXXXX
GITLAB_WEBHOOK_TOKEN=XXXXXXXX
JIRA_TOKEN=XXXXXXX
JIRA_USERNAME=XXXXXX
JIRA_URL=https://XXXXXXXXX
JIRA_PROJECT=SS
```

Note:  In order to highly customize the yaml configuration for CxFlow in
a Docker environment use this docker image as a base and add custom
configuration.  Alternatively build from source (Dockerfiles are found
in the git repository)

## Command Line

There are various ways to integrate CxFlow via command line.

Here is a list of command line arguments / flags to help drive the
different execution flows and overrides.

| Option                   | Description                                                                                                                                                                                                                                                                                                                                                                                       |
|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| --spring.config.location | Override the main application.yml/properties file for the application.  Defaults to the application.yml packaged within the jar                                                                                                                                                                                                                                                                   |
| --parse                  | Indicates that a result XML file from Checkmarx will be provided (–f will also be mandatory).  No value is given - this is a flag                                                                                                                                                                                                                                                                 |
| --project                | Indicates that we would like to retrieve the latest scan results for a given team/project and provide feedback (defect / issue tracking).  No value is given - this is a flag                                                                                                                                                                                                                     |
| --batch                  | Indicates that the entire instance, or a given team will be iterated through and the latest results will be retrieved for each project and feedback will be provided (defect / issue tracking)                                                                                                                                                                                                    |
| --cx-team                | Used to override the team that will be used as a base team (optionally defined globally in the yaml configuration.  This team will be used when creating a project in source/scan (zip) mode, as well as the team to use when retrieving latest project results in project/batch modes (--project/--batch)                                                                                        |
| --cx-project             | Used to create the project in source/scan (zip) mode as well as indicate which project to retrieve the latest results for in project mode (--project)                                                                                                                                                                                                                                             |
| --namespace              | Repository group (Gitlab)/organization (Github)/namesapce (BitBucket). Used as higher level grouping of repositories.  Used along with repo-name and branch for tracking purposes (Jira Only).  If these 3 are not present, then application attribute must be passed (**--app**).  These values are stored in a Tracking label within Jira.  This value is also stored in the body of the issue. |
| --repo-name              | Name of the repository.  Used along with repo-name and branch for tracking purposes (Jira Only).  If these 3 are not present, then application attribute must be passed (--app).  These values are stored in a Tracking label within Jira.  This value is also stored in the body of the issue.                                                                                                   |
| --branch                 | Branch Used along with repo-name and branch for tracking purposes (Jira Only).  If these 3 are not present, then application attribute must be passed  (--app).  These values are stored in a Tracking label within Jira. This value is also stored in the body of the issue.                                                                                                                     |
| --app                    | Alternatively used for Tracking purposes.  This value is also stored in the body of the issue.                                                                                                                                                                                                                                                                                                    |
| --repo-url               | Required if issues tracking with GitHub Issues or GitLab Issues.  This value is also stored in the body of the issue.                                                                                                                                                                                                                                                                             |
| --f                      | File to be processed.  This the output from Checkmarx CLI, Jenkins/Bamboo Plugin, etc                                                                                                                                                                                                                                                                                                             |
| --exclude-files          | File exclusions to apply when running --scan CLI execution                                                                                                                                                                                                                                                                                                                                        |
| --exclude-folders        | Folder exclusions to apply when running --scan CLI execution                                                                                                                                                                                                                                                                                                                                      |
| --config                 | Optional.  Configuration override file (JSON).                                                                                                                                                                                                                                                                                                                                                    |
| --bbs                    | Optional. Indicates the repository is of type BitBucket Server as BB Server follows a different url file format                                                                                                                                                                                                                                                                                   |
| --bb                     | Optional. Indicates the repository is of type BitBucket Cloud as BB Cloud follows a different url file format (it is also different than BB Server)                                                                                                                                                                                                                                               |
| --bug-tracker            | Optional.  Used to override the globally configured bug tracker as defined by the base YAML configuration.  The name here must match the exact bean name as specified in the --bug-tracker-impl list of available implementation, and is case sensitive (JIRA is the only option that is not within this list and can be used as well).                                                           |
| --spring.config.location | Path to application.yml file that contains global configuration for CxFlow.  This is only required if the jar file and the application.yml file is not in the current working directory.  See [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) (section 24.3)                                                       |
| --offline                | If this flag is provided, the Checkmarx instance will not be contacted.  This means there will be no issue description provided and there will be no ability to work with Checkmarx custom fields.                                                                                                                                                                                                |
| --blocksysexit           | Optional. Mainly for build/test purpose. Avoid System.exit() in the code and exit with java exception                                                                                                                                                                                                                                                                                             |

### Parse

``` bash
java -jar cx-flow-<ver>.jar  \
--parse \
--namespace=checkmarx \
--repo-name=Riches.NET \
--repo-url=https://github.com/xxxx/xxxx.git \
--branch=master \
--app=ABC \
--f=Checkmarx/Reports/ScanReport.xml 
```

### Batch

#### Entire Instance

``` bash
java -jar cx-flow-<ver>.jar --batch
```

#### Specific Team 

``` bash
java -jar cx-flow-<ver>.jar --batch --cx-team="CxServer\SP\Checkmarx\development"
```

### Single Project

``` java
java -jar cx-flow-<ver>.jar \
--project \
--cx-team="CxServer\SP\Checkmarx\Test" \
--cx-project="riches-master" \
--app=AppName
```

### Docker

``` java
docker pull checkmarxts/cxflow
docker run checkmarxts/cxflow <applicable parameters>
```

  

  

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [Re\_
Thailand .msg](attachments/1276510287/1554186340.msg)
(application/vnd.ms-outlook)  
