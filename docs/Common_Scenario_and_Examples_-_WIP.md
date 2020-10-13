# Common Scenario and Examples - WIP

-   Scan on every pull request to develop and security and publish
    issues to Jira if merge to master
-   Scan on every pull request to master with support for incremental
    scanning
-   Get a JSON file of the latest scan for a given project
-   Get all Cx XML reports for each project latest scan results for a
    given checkmarx team
-   Publish issues to Jira after Checkmarx Jenkins plugin is finished
    scanning
-   Trigger scan and output csv from a YAML/Docker based CI tool
    (CircleCI, GitLab, Drone)  
    -   GitLab CI Examples

        ``` java
        image: docker:latest
        services:
          - docker:dind

        variables:
            CX_PROJECT: \CxServer\SP\Checkmarx\riches-test.net

        stages:
          - sast
        #  - results

        sast-scan:
          stage: sast
          image:
            name: custodela/checkmarx-cli
            entrypoint: ['']
          script:
            - ${CX_CLI} Scan -v -CxServer ${CHECKMARX_BASE_URL} -projectName ${CX_PROJECT} -CxUser ${CHECKMARX_USERNAME} -CxPassword ${CHECKMARX_PASSWORD} -Locationtype folder -locationpath ${CI_PROJECT_DIR} -ReportXML cx.xml
            - cp /cx/CxConsolePlugin-8.80.0/riches.net/cx.xml .
          only:
            - master
          artifacts:
            paths:
              - ./cx.xml

        cx-scan:
          stage: sast
          image:
            name: custodela/cx-flow:cmd
            entrypoint: ['']
          script:
            - ${MACHINA_CLI}
                --scan
                --cx-team="\CxServer\SP\Checkmarx"
                --cx-project="Abc1234"
                --app=ABCAPP
                --f=.
                --bug-tracker=Json
          only:
            - master

        cx-results:
          stage: results
          image:
            name: custodela/cx-flow:cmd
            entrypoint: ['']
          script:
            - ${MACHINA_CLI}
                --project
                --cx-team="\CxServer\SP\Checkmarx"
                --cx-project="Abc1234"
                --app=ABCAPP
                --cx-flow.break-build=true
          only:
            - master
        ```
