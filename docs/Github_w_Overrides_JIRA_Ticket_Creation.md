# Github w/ Overrides & JIRA Ticket Creation

**Lab Goals**

This lab is designed to teach the following topics:

-   Automated ticket creation for JIRA using CxFlow in Webhook mode

-   Organization level webhooks

-   Webhook overrides

-   Branching & scanning best practices

**Lab Prerequisites**

-   [Github Webhook Lab](Github_Webhook_Lab)

-   [CxFlow CLI & JIRA Configuration](CxFlow_CLI_JIRA_Configuration)

**CxFlow Prep**

-   CxFlow & JDK should already be downloaded from the previous labs

-   Create a new file called application-github2jira.yml in C:\\Flow
    with the text at the bottom of the page and replace any values
    surrounded in \#\#\#\<\>\#\#\# with your appropriate values

-   Go over all of the config items and read what they do

    -    [CxFlow Configuration](CxFlow_Configuration)

    -   [Bug Trackers / Feedback
        Channels](Bug_Trackers_Feedback_Channels)

**Github Prep**

-   Create a new organization called \<yourname\>-cxflowgithub

-   Click create New repository\> Import a repository

    -   Your old repository’s clone URL =
        https://github.com/CSPF-Founder/JavaVulnerableLab.git

    -   Owner = \<yourname\>-cxflowgithub

    -   Name = github2jira\_jvl

    -   Privacy = Private

-   Create an organization level webhook by selecting your
    organization - \<yourname\>-cxflowgithub

    -   [WebHook
        Registration](https://checkmarx.atlassian.net/wiki/spaces/PTS/pages/1277722692/WebHook+Registration)

    -   Click Settings\>Webhooks\>Add Webhook and fill in details

        -   Payload URL = ngrok location of cxflow that is running -
            example:
            [http://4d91e7ed.ngrok.io](http://4d91e7ed.ngrok.io/)

        -   Content type = application/json

        -   Secret = webhook-token: from .yml file - example: 12345

        -   Let me select events - Push

        -   Click Add Webhook - there should be a checkmark next to the
            hook name now

    -   Add a second webhook with an override for pull requests

    -   [CxFlow
        Configuration](CxFlow-Configuration_1276641334.html#CxFlowConfiguration-WebHookURLOverrideParameters-Details(relatedtoabove)) -
        override config info

        -   Payload URL = ngrok location of cxflow that is running +
            override - example:
            [http://4d91e7ed.ngrok.io](http://4d91e7ed.ngrok.io/)?project=repo-pr

        -   Content type = application/json

        -   Secret = webhook-token: from .yml file - example: 12345

        -   Let me select events - Pull requests

        -   Click Add Webhook - there should be a checkmark next to the
            hook name now

**Triggering CxFlow from a Push to a protected branch (develop)**

-   After .YML file is completely filled out and saved

-   Start CxFlow in webhook mode by opening a CMD prompt and typing the
    following

``` java
cd C:\CxFlow
java -jar cx-flow-1.5.4.jar --spring.config.location="C:\CxFlow\application-github2jira.yml" --web
```

-   Open your favorite IDE of choice and clone the new repo

    -   This lab will use Intellij & the repo -
        https://github.com/sam-cxflowgithub/github2jira\_jvl.git

        -   Open IntelliJ, click Check out from Version Control and
            input above URL, select next until done

-   Create a new local branch called develop

    -   Click VCS\>Git\>Branches\>New Branch

    -   Type "develop" and click ok

    <!-- -->

    -   Open [README.md](http://readme.md/) and add the following line &
        save

        -   develop-push-Test1

    -   Commit to local git repo & push to origin with comments by
        clicking the following

        -   VCS\>Git\>Commit File & enter the following commit message

        -   Cxflow push to a protected branch

        -   Click commit & push

        -   Click Push & enter Github credentials on popup

            -   Username = username

            -   Password = token created

-   You should now see a scan in the CxSAST queue

    -   Notice the project name = RepoName-Branch

    -   Notice the team of the new project = GitHub organization

        -   This is due to the team line in the .yml file - it
            auto-creates a team if it does not exist

        -   This can be overridden and changed in the configs

-   Once the scan finishes, open the APPSEC project in Jira and use the
    search ability to look for the SQL Injection in the
    LoginValidator.java page of the \[develop\] branch

**Triggering CxFlow from a Pull Request to a protected branch (master)**

-   Create a new local branch called fix/APPSEC-\#\# where \#\# is the
    ticket created in JIRA

    -   Click VCS\>Git\>Branches\>New Branch

    -   Type "fix/APPSEC-\#\#" and click ok

-   Replace with the following code block starting at line 50-52 of
    JavaVulnerableLab\\src\\main\\java\\org\\cysecurity\\cspf\\jvl\\controller\\LoginValidator.java

``` java
//Statement stmt = con.createStatement();
//Sanitized User and Password
String sql = "select * from users where username=? and password=?"; 
PreparedStatement preparedStatement = con.prepareStatement(sql);
// rs=stmt.executeQuery("select * from users where username='"user"' and password='"pass"'"
preparedStatement.setString(1,user);
preparedStatement.setString(2,pass);
ResultSet rs = preparedStatement.executeQuery(); 
```

-   Add the following line on line 21 of the same file

``` java
import java.sql.PreparedStatement;
```

-   Save the file, commit to the local repo and push to origin

    -   Click File \> Save All

    -   Click VCS\>Git\>Commit File & add commit message "added
        preparedstmt on line 50"

    -   Click Commit and Push followed by Push

        -   Ignore any warnings like a true developer
            <img src="images/icons/emoticons/tongue.png" class="emoticon emoticon-cheeky" alt="(tongue)" />

Notice that this branch did not meet the criteria for scanning In the
cxflow logs

-   Open Github and click Compare & pull request for the fix/APPSEC-\#\#
    branch

    -   Select develop for the branch to merge into

    -   Click Create pull request

Notice that this branch will scan into a common project ending in -pr.

Now, let's merge to develop and follow our defined [Branching
Strategy](https://checkmarx.atlassian.net/wiki/spaces/PTS/pages/1576993180/Branching+Strategy)
by creating a pull request to master

Notice that the merge performed a scan because of the push to develop &
that APPSEC-\#\# is DONE in JIRA

Note how all the scans are under two projects in CxSAST

-   The LoginValidtor.java SQLi is gone from the
    comments<img src="images/icons/emoticons/biggrin.png" class="emoticon emoticon-laugh" alt="(big grin)" />

-   Click Merge Pull Request\> Confirm Merge to accept the risk CxSAST
    has posted about in the comments

**To Demo**

-   Save & cleanup C:\\CxFlow\\flow.log to for showing what happens via
    automation

-   Do not delete the pull request branch or modify the projects

-   Change branches in IntelliJ in the bottom right corner and force
    checkout as long as you don’t clone again

-   Walk back through the workflow without the setup to show demo

The below YAML file is for 8.9 - please update using directions on
[Workflow Labs & Guides](Workflow_Labs_Guides) for 9.0
