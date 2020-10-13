# Support

  

The [CxUtils Project](https://github.com/checkmarx-ts/CxUtils) has added
a
[sub-project](https://github.com/checkmarx-ts/CxUtils/tree/master/CxFlowDemoInstance)
that contains scripting that creates a full path demonstration
environment for Cx-Flow. It uses Docker containers to create a local
Jira instance with Cx-Flow running as a webhook endpoint.  It can be
used as a demonstration or development environment as needed.  All
options are customizable with a default configuration that should allow
a fully working environment to be created on your local machine in under
10 minutes.

This can be used to demonstrate scenarios including but not limited to:

-   Cx-Flow invoking a scan upon receiving a webhook event, pushing
    results into Jira as issues
-   Invoking Cx-Flow from the shell on demand, pushing results into Jira
    as issues
-   Invoking Cx-Flow from Jenkins, pushing results into Jira as issues

  
