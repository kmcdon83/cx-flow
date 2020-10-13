# WebHook Registration

-   [Namespace WebHooks](#WebHookRegistration-NamespaceWebHooks)
-   [GitHub](#WebHookRegistration-GitHub)
-   
-   [GitLab](#WebHookRegistration-GitLab)
-   
-   [Azure DevOps](#WebHookRegistration-AzureDevOps)
-   [Bitbucket Server](#WebHookRegistration-BitbucketServer)
-   [Bitbucket Cloud](#WebHookRegistration-BitbucketCloud)

The url/endpoint for all webhook registrations are as follows:

<http://cxflow> \| <https://cxflow> 

<http://cxflow/cx> \| <https://cxflow/cx> 

/cx is an applicable context that can be used, but the default / root
context will

Note: replace cxflow with end point/port that you are running the
webservice (i.e. localhost:8080)

## Namespace WebHooks

WebHooks can be registered at the namespace level (Organization in
GitHub, Group within GitLab, Team in Bitbucket).  This will apply the
WebHook configuration globally for all Repositories underneath within
the hierarchy.

## GitHub

When registering the webhook in GitHub, ensure the **application/json**
*Content type* is selected.  Form URL Encoded is not supported. 

The secret must be the pre-shared token that the CxFlow webservice is
using to validate and authenticate requests.

The supported events are **Pull Request, **which will by default produce
the result feedback within the pull request itself, and **Push
Event, **which will execute the desired bug tracker implementation.

## GitLab

## Azure DevOps

Azure DevOps requires a different endpoint for Pull and Push events due
to the fact the payload and headers cannot be differentiated. 

-   When registering Pull Create Events, use
    **http://\<cxflow\>/ado/pull **
-   When registering Push Events, use **http://\<cxflow\>/ado/push **

Note: Only Push/Pull Create events are currently supported. Token should
be sent as Basic Authentication Header.

## Bitbucket Server

Similar to cloud, but requires a shared secret field, which is used to
sign/authenticate the request.

## Bitbucket Cloud

Bitbucket cloud does not support a shared key/secret for digitally
signing and verifying the request, so we require the token paramater to
be passed (<http://cxflow?token=XXXXX),> XXXX is the pre-shared token
that the CxFlow webservice is using to validate and authenticate
requests.

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[image2019-5-4\_14-32-22.png](attachments/1277722692/1277689899.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[image2019-5-4\_14-41-3.png](attachments/1277722692/1277689910.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[image2019-5-4\_14-49-27.png](attachments/1277722692/1277657141.png)
(image/png)  
