# GitLab Webhook Lab

**Lab Goals**

This lab is designed to teach the following topics:

-   How to scan on a Merge Request to a Protected Branch

-   How to scan on a Push to Protected Branch

-   GitLab Issue Creation on a Push to Protected Branch

**CxFlow Prep**

-   Everything should be performed on the same machine that the CxSAST
    Manager is running for this lab

    -   Alternatively, do this on your local machine and change
        checkmarx base-url: in the .yml file to something that GitLab
        can reach example: <https://cxprivatecloud.checkmarx.net/>

-   Create a new folder in C:\\ called CxFlow and download the latest
    CxFlow .jar for JDK 8 into this folder from
    <https://github.com/checkmarx-ts/cx-flow/releases>

    -   The current release of this lab guide =  cx-flow.1.5.4.jar

-   Download the latest windows version of ngrok from
    <https://ngrok.com/download> & unzip to C:\\CxFlow without the
    containing folder

-   Create a new file called application-gitlab.yml in C:\\Flow with the
    text at the bottom of the page and replace any values surrounded in
    \#\#\#\<\>\#\#\# with your appropriate values

-   Go over all of the config items and read what they do

    -    [CxFlow Configuration](CxFlow_Configuration)

-   Start Ngrok by opening a CMD prompt and typing the following

``` java
cd C:\CxFlow
ngrok http 8982
```

**GitLab Prep**

-   Create an account at [www.gitlab.com](http://www.gitlab.com) with
    your Checkmarx email

    -   Alternatively, you can connect using GitHub if you have finished
        that lab

-   Create a new private group called \<yourname\>-checkmarx

-   Create a new subgroup called GitLab CxFlow

-   Create a new private project called CxFlowGitLab

    -   Import code from your favorite small demo codebase on github

        -   This lab will use - <https://github.com/psiinon/bodgeit>

    -   Click Import Project \> Repo By URL

        -   Git Repository URL = <https://github.com/psiinon/bodgeit>

        -   Project Name = CxFlowGitLab

        -   Ensure Private is selected & click Create Project

<!-- -->

-   Create a token by clicking your profile in upper right
    corner \>settings

    -   Click Access Tokens & add a personal access token

    -   Give the token api, read\_user, write\_repository,
        read\_registry scopes

    -   Copy this token and keep safe - it should be pasted into the
        token: \<\> of the application-gitlab.yml

-   After .YML file is completely filled out and saved

-   Start CxFlow in webhook mode by opening a CMD prompt and typing the
    following

``` java
cd C:\CxFlow
java -jar cx-flow-1.5.4.jar --spring.config.location="C:\CxFlow\application-gitlab.yml" --web
```

-   Create a webhook by selecting Projects\>Your Projects & select the
    new repo you just created

    -   [WebHook Registration](WebHook_Registration)

-   Click Settings\>Webhooks and fill in details

    -   URL = ngrok location of cxflow that is running - example:
        <http://4d91e7ed.ngrok.io>

    -   Secret = webhook-token: from .yml file - example: 12345

    -   Trigger = Push events, Merge request events

    -   Click Add Webhook

**Triggering CxFlow from a Push to a protected branch (master)**

-   Open your favorite IDE of choice and clone the new repo

    -   This lab will use Intellij & the repo -
        <https://gitlab.com/scxbush/cxflowgitlab>

        -   Open IntelliJ, click Check out from Version Control and
            input above URL, select next until done

    -   Open [README.md](http://README.md) and add the following line &
        save

        -   CxFlowMasterPush-Test1

    -   Commit to local git repo & push to origin with comments by
        clicking the following

        -   VCS\>Git\>Commit File & enter the following commit message

        -   Cxflow push to a protected branch

        -   Click commit & push

        -   Click Push & enter GitLab credentials on popup

            -   Username = username

            -   Password = token created

-   You show now see a scan in the CxSAST queue

    -   Notice the project name = RepoName-Branch

    -   Notice the team of the new project = GitLab organization

        -   This is due to the team line in the .yml file - it
            auto-creates a team if it does not exist

        -   This can be overridden and changed in the configs

-   Once the scan finishes you should see issues in the issue tab on
    your GitLab project

    -   https://gitlab.com/\<yourorg\>/cxflowgitlab/issues

    -   Examine the following issue CX SQL\_Injection @ root/basket.jsp
        \[master\]

    -   Open the Checkmarx link and examine the finding

**Triggering CxFlow from a Pull Request to a protected branch
(security-fix to master)**

-   Open Intellij and create a new local branch called security-fix

    -   Click VCS\>Git\>Branches\>New Branch

    -   Type "security-fix" and click ok

-   Open basket.jsp underneath the root folder and replace lines 53-55
    with the following

``` java
//Statement stmt = conn.createStatement();
//Security Fix
PreparedStatement preparedStatement = con.prepareStatement(sql);
try {
//ResultSet rs = stmt.executeQuery("SELECT * FROM Baskets WHERE basketid = " + basketId);
String sql = "SELECT * FROM Baskets WHERE basketid =?");
preparedStatement.setString(1, basketId);
ResetSet rs = preparedStatement.executeQuery();
```

-   Add the following on line 7 to import the correct package

``` java
<%@ page import="java.sql.PreparedStatement" %>
```

-   Alternatively, you can pull down results using the CxViewer in
    Intellij and remediate based on the ID

-   Save the file, commit to the local repo and push to origin

    -   Click Save All

    -   Click VCS\>Git\>Commit File & add commit message "added
        preparedstmt on line 55"

    -   Click Commit and Push followed by Push

-   Go to GitLab and Merge Requests on your project page

    -   Click New Merge request

    -   Select cxflowgitlab security-fix as the source branch

    -   Select cxflowgitlab master as the target branch

    -   Click Compare branches and continue

    -   Ensure the title does not have WIP: & click submit merge request

-   Notice in GitLab that some checks haven't been completed yet -
    Checkmarx Scan

-   Notice a new scan in CxSAST with Project name = RepoName-Branch

    -   Once the scan finishes, you can see the post in the GitLab merge
        request comments with all the vulnerabilities found

    -   Notice that the basket.jsp SQLi is gone
        <img src="images/icons/emoticons/biggrin.png" class="emoticon emoticon-laugh" alt="(big grin)" />

    -   Click Merge & uncheck “Delete source branch” to accept the risk
        CxSAST has posted about in the comments

Normally we would delete the source branch, but we should keep it around
for demo purposes

-   Notice a new scan in CxSAST with Project name = RepoName-Branch for
    the master branch

-   Go back to the GitLab issues page and note that there are only 15
    issues remaining

-   Go to CxSAST and note that cxflowgitlab-master project has solved
    issues and recurrent issues

**To Demo**

-   Save & cleanup C:\\CxFlow\\flow.log to for showing what happens via
    automation

-   Do not delete the pull request branch or modify the projects

-   Change branches in IntelliJ in the bottom right corner and force
    checkout as long as you don’t clone again

-   Walk back through the workflow without the setup to show demo

The below YAML file is for 8.9 - please update using directions on
[Workflow Labs & Guides](Workflow_Labs_Guides) for 9.0
