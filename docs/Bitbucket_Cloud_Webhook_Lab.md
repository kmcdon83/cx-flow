# Bitbucket Cloud Webhook Lab

**Lab Goals**

This lab is designed to teach the following topics:

-   How to scan on a Merge Request to a Protected Branch

-   How to scan on a Push to Protected Branch

-   How to work with version control & remediate code in Eclipse IDE

**CxFlow Prep**

-   Everything should be performed on the same machine that the CxSAST
    Manager is running for this lab

    -   Alternatively, do this on your local machine and change
        checkmarx base-url: in the .yml file to something that Bitbucket
        can reach example: <https://cxprivatecloud.checkmarx.net/>

-   Create a new folder in C:\\ called CxFlow and download the latest
    CxFlow .jar for JDK 8 into this folder from
    <https://github.com/checkmarx-ts/cx-flow/releases>

    -   The current release of this lab guide =  cx-flow.1.5.4.jar

-   Download the latest windows version of ngrok from
    <https://ngrok.com/download> & unzip to C:\\CxFlow without the
    containing folder

-   Create a new file called application-bitbucket.yml in C:\\Flow with
    the text at the bottom of the page and replace any values surrounded
    in \#\#\#\<\>\#\#\# with your appropriate values

-   Go over all of the config items and read what they do

    -    [CxFlow Configuration](CxFlow_Configuration)

-   Start Ngrok by opening a CMD prompt and typing the following

``` java
cd C:\CxFlow
ngrok http 8982
```

**Bitbucket Prep**

-   Login to [www.bitbucket.org](http://www.bitbucket.org) with your
    Checkmarx email (Atlassian id)

-   Create a new private repository named CxFlowBitbucket by clicking
    the + button on the sidebar

-   Click Import repository to import code from your favorite small demo
    codebase on GitHub

    -   This lab will use - https://github.com/psiinon/bodgeit

-   Create a token by clicking your profile in lower-left corner &
    Bitbucket settings

    -   Click App Passwords & Create app password

    -   Give the token all access to repositories, pull requests, &
        webhooks

    -   Copy this token and keep safe - it should be pasted into the
        token: \<\> of the application-bitbucket.yml

        -   Note that the token in the YML file should follow the format
            \<UserName\>:\<Token\>

-   After .YML file is completely filled out and saved

-   Start CxFlow in webhook mode by opening a CMD prompt and typing the
    following

``` java
cd C:\CxFlow
java -jar cx-flow-1.5.4.jar --spring.config.location="C:\CxFlow\application-bitbucket.yml" --web
```

-   In Bitbucket, create a webhook by selecting Repositories & select
    the new repo you just created

    -   [WebHook Registration](WebHook_Registration)

-   Click Settings\>Webhooks\>Add Webhook and fill in details

    -   Title = CxFlow

    -   URL = ngrok location of cxflow that is running + ?token=webtoken
        from yml file - example: <http://4d91e7ed.ngrok.io>?token=12345

-   Choose from a full list of triggers = Push, Pull Request Created

-   Click Save

**Triggering CxFlow from a Push to a protected branch (master)**

-   Open your favorite IDE of choice and clone the new repo

    -   This lab will use Eclipse & the repo -
        https://bitbucket.org/samqbush/cxflowbitbucket/

        -   Open Eclipse, click File \> Import \> Git \> Projects from
            Git (Next) \>Clone URI (Next)

        -   URI = your repo

        -   Authentication User = Bitbucket username

        -   Password = token you created earlier

        -   Select Store in Secure Store

        -   Select next until Import as a general project, then click
            Next followed by Finish

    -   Right click [README.md](http://README.md) and open with Generic
        Text Editor, add the following line & save

        -   CxFlowMasterPush-Test1

    -   Stage the [Readme.md](http://Readme.md) file, commit to local
        git repo & push to origin with comments by clicking the
        following

        -   Window\>Show View\>Other…\>Git\>Git Staging

        -   Select [README.md](http://README.md) & click the + button

        -   Enter the following into the Commit Message window

            -   Cxflow push to a protected branch

        -   Click Commit & Push

-   You show now see a scan in the CxSAST queue

    -   Notice the project name = RepoName-Branch

    -   Notice the team of the new project = Bitbucket organization

        -   This is due to the team line in the .yml file - it
            auto-creates a team if it does not exist

        -   This can be overridden and changed in the configs

-   \<add Jira bug tracking\>

**Triggering CxFlow from a Pull Request to a protected branch
(security-fix to master)**

-   Open Eclipse and create a new local branch called security-fix

    -   Click Window\>Show View\>Other…\>Git Repositories

    -   Right click Branches\>Switch To\>New Branch

    -   Type "security-fix" and click Finish

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
    Eclipse and remediate based on the ID

-   Save the file, stage the file, commit to the local repo and push to
    origin

    -   Click Save All

    -   Click Git Staging\>basket.jsp and then + button then add commit
        message "added preparedstmt on line 55"

    -   Click Commit and Push followed by Next and then Finish

-   Go to Bitbucket and Branches on your repository page

    -   Click Create in the Pull Request column and on the security-fix
        row

    -   Click Create Pull Request

-   Notice in Bitbucket that a comment has been made - Scan submitted to
    Checkmarx

-   Notice a new scan in CxSAST with Project name = RepoName-Branch

    -   Once the scan finishes, you can see the post in the Bitbucket
        merge request comments with all the vulnerabilities found

    -   Notice that the basket.jsp SQLi is gone
        <img src="images/icons/emoticons/biggrin.png" class="emoticon emoticon-laugh" alt="(big grin)" />

    -   Click Merge & uncheck “Close source branch” to accept the risk
        CxSAST has posted about in the comments

Normally we would delete the source branch, but we should keep it around
for demo purposes

-   Notice a new scan in CxSAST with Project name = RepoName-Branch for
    the master branch

-   Go JIRA issues page and note that there are only 15 issues remaining

-   Go to CxSAST and note that cxflowbitbucker-master project has solved
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
