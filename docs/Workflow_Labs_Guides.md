# Workflow Labs & Guides

**Labs were designed for CxSAST v8.9 -** the following updates in the
.yml file are required underneath the checkmarx tag for 9.0+: version,
scope, & team now requires / instead of \\

``` xml
checkmarx:
  version: 9.0
  scope: access_control_api sast_rest_api
  team: /CxServer/SP/Company
```

**Webhook Mode PreReqs**

GIT configured in Settings\>Application Settings\>General in CxSAST
Manager

YAML templates for demos & PoCs can also be found at
<https://github.com/checkmarx-ts/cicd-examples/tree/master/cxflow/YML%20templates>

[Github Webhook Lab](Github_Webhook_Lab)

-   Scan on Pull Request to a Protected Branch

-   Scan on Push to Protected Branch

-   Github Issue Creation on Push to Protect Branch

-   How to work with version control & remediate code in IntelliJ IDE

[GitLab Webhook Lab](GitLab_Webhook_Lab)

-   Scan on Pull Request to a Protected Branch

-   Scan on Push to Protected Branch

-   GitLab Issue Creation on Push to Protect Branch

-   How to work with version control & remediate code in IntelliJ IDE

[Bitbucket Cloud Webhook Lab](Bitbucket_Cloud_Webhook_Lab)

-   Scan on Pull Request to a Protected Branch

-   Scan on Push to Protected Branch

-   How to work with version control & remediate code in Eclipse IDE

[Azure DevOps Webhook Lab](Azure_DevOps_Webhook_Lab)

-   How to Commit & Push code changes using Visual Studio Code

-   How to scan on a Merge Request to a Protected Branch

-   How to scan on a Push to Protected Branch

-   Azure Work Item creation on a Push to Protected Branch

-   Update, Open or Close tickets from Batch mode via CLI

[CxFlow CLI & JIRA Configuration](CxFlow_CLI_JIRA_Configuration)

-   How to configure a Jira project for CxFlow

-   Automated ticket creation using CxFlow CLI

-   Scanning via CxFlow CLI

[Github w/ Overrides & JIRA Ticket
Creation](Github_w_Overrides_JIRA_Ticket_Creation)

-   Automated ticket creation for JIRA using CxFlow in Webhook mode

-   Organization level webhooks

-   Webhook overrides

[CxFlow Batch Mode Email Notification of Proposed Not
Exploitables](CxFlow_Batch_Mode_Email_Notification_of_Proposed_Not_Exploitables)

-   Run CxFlow in batch mode with XML results output

-   Automate email notifications of PNEs

**Additional Guides**

-   [CxFlow Nightly Batch Mode](CxFlow_Nightly_Batch_Mode)

-   [CxFlow Azure DevOps Pipelines](CxFlow_Azure_DevOps_Pipelines)

-   [CxFlow Azure DevOps Web Hook
    Integration](CxFlow_Azure_DevOps_Web_Hook_Integration)
