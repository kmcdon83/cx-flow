# CxFlow CLI & JIRA Configuration

**Lab Goals**

This lab is designed to teach the following topics:

-   How to configure a Jira project for CxFlow

-   Automated ticket creation using CxFlow CLI

-   Scanning via CxFlow CLI

**CxFlow Prep**

-   Create a new folder in C:\\ called CxFlow and download the latest
    CxFlow .jar for JDK 8 into this folder from
    <https://github.com/checkmarx-ts/cx-flow/releases>

    -   The current release of this lab guide =  cx-flow.1.5.4.jar

-   Create a new file called application-jira.yml in C:\\Flow with the
    text at the bottom of the page and replace any values surrounded in
    \#\#\#\<\>\#\#\# with your appropriate values

-   Go over all of the config items and read what they do

    -    [CxFlow Configuration](CxFlow_Configuration)

    -   [Bug Trackers / Feedback
        Channels](Bug_Trackers_Feedback_Channels)

**Jira Prep**

-   Open an Incognito browser window

-   Sign up for free cloud account using a personal or fake email

    -   <https://www.atlassian.com/try/cloud/signup?bundle=jira-software&edition=free>

You cannot use your Checkmarx account as we have Atlassian accounts for
Confluence

-   During the auto-setup choose the following options

    -   I am experienced with Jira

    -   My team is experienced with agile methodologies

    -   We spend our time working on fixing bugs

    -   We have a flexible schedule to finish our work

-   Alternatively, create a new project & choose a Kanban project

-   Project Name = APPSEC

-   Project Key = APPSEC

-   Create an API token from your Atlassian account: 

    -   Log in to <https://id.atlassian.com/manage/api-tokens>.

    -   Click Create API token.

    -   From the dialog that appears, enter ‘CxFlow’ and click Create.

    -   Click Copy to clipboard, then paste the token to your script, or
        elsewhere to save: it should be pasted into the token: \<\> of
        the application-jira.yml

Optional - watch a customer a walkthrough of the JIRA config -
[Microsoft
Stream](https://web.microsoftstream.com/video/9022c814-2eb7-4df9-9e2f-273e30941c2b?channelId=ef493133-a10e-4582-94a4-60d40ff31c19)

Explanations of custom fields in Jira start around minute 55

-   Create a custom field for this project and issue type screen by
    clicking the settings wheel in the top right corner

    -   Click Issues\>Custom Fields\>Create custom field

    -   Click Labels & give it a name “Application”

    -   Description = CxSAST Project

    -   Select the checkboxes next to APPSEC: Kanban Bug Screen &
        APPSEC: Kanban Default Issue Screen

    -   Click Update

-   Create another custom field for Category

    -   Name = Category

    -   Description = CxSAST Vulnerability Type

    -   Select the checkboxes next to APPSEC: Kanban Bug Screen &
        APPSEC: Kanban Default Issue Screen

    -   Click Update

**Triggering CxSAST scan & JIRA ticket creation from CxFlow CLI**

-   After the .YML file is completely filled out and saved

-   The following command clones a github repo, creates a CxSAST scan
    for the cloned repo, & creates tickets according to the .yml file

Don’t forget to change the \\ to / if you are using 9.0
<img src="images/icons/emoticons/wink.png" class="emoticon emoticon-wink" alt="(wink)" />
CxServer/SP/Company

``` java
cd C:\CxFlow
git clone https://github.com/ethicalhack3r/DVWA.git 
cd C:\CxFlow
java -jar cx-flow-1.5.4.jar --spring.config.location="C:\CxFlow\application-jira.yml" --scan --f="./DVWA" --cx-team="CxServer\SP\Company" --cx-project="DVWA" --app="DVWA"
```

**Triggering only JIRA ticket creation from CLI using the batch
command**

-   After the .YML file is completely filled out and saved

-   The following command opens tickets for a CxSAST project’s last
    finished scan according to the .yml file

``` java
cd C:\CxFlow
java -jar cx-flow-1.5.4.jar --spring.config.location="C:\CxFlow\application-jira.yml" --project --cx-team="CxServer\SP\Company" --cx-project="DVWA" --app="DVWA"
```

-   Open the APPSEC project in Jira and note the vulnerabilities that
    have been opened

**Bonus**

-   You can kick off batch mode ticket creation in any Linux pipeline by
    supplying the application.yml file and using the following code to
    download CxFlow & run

    -   Replace project & app with an environment variable depending on
        the pipeline

``` java
apk add --update curl
curl -O -k https://github.com/checkmarx-ts/cx-flow/releases/download/1.5.4/cx-flow-1.5.4.jar
java -jar cx-flow-1.5.4.jar --spring.config.location="./application-jira.yml" --scan --f=. --cx-team="CxServer" --cx-project="Bodgeit" --app="Bodgeit"
```

The below YAML file is for 8.9 - please update using directions on
[Workflow Labs & Guides](Workflow_Labs_Guides) for 9.0
