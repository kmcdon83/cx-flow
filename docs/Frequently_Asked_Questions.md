# Frequently Asked Questions

Here is a list of frequently asked questions that are asked when it
comes to CxFlow.

-   [Q: Whats is CxFlow?](#FrequentlyAskedQuestions-Q:WhatsisCxFlow?)
-   [Q: Why does this benefit
    customers?](#FrequentlyAskedQuestions-Q:Whydoesthisbenefitcustomers?)
-   [Q: What are some of the CxFlow use
    cases?](#FrequentlyAskedQuestions-Q:WhataresomeoftheCxFlowusecases?)
-   [Q: Is CxFlow supported by the product
    team?](#FrequentlyAskedQuestions-Q:IsCxFlowsupportedbytheproductteam?)
-   [Q: Does CxFlow have Checkmarx Licensing
    requirements?](#FrequentlyAskedQuestions-Q:DoesCxFlowhaveCheckmarxLicensingrequirements?)
-   [Q: What Integrations does CxFlow
    support?](#FrequentlyAskedQuestions-Q:WhatIntegrationsdoesCxFlowsupport?)
-   [Q: What are the requirements for
    CxFlow?](#FrequentlyAskedQuestions-Q:WhataretherequirementsforCxFlow?)
-   [Q: How can I demo CxFlow to a
    customer?](#FrequentlyAskedQuestions-Q:HowcanIdemoCxFlowtoacustomer?)
    -   [EMEA](#FrequentlyAskedQuestions-EMEA)
    -   [NA](#FrequentlyAskedQuestions-NA)
    -   [APAC](#FrequentlyAskedQuestions-APAC)
-   [Q: Is CxFlow open
    source?](#FrequentlyAskedQuestions-Q:IsCxFlowopensource?)
-   [Q: Have CxFlow been security tested and scanned for known
    vulnerabilities?](#FrequentlyAskedQuestions-Q:HaveCxFlowbeensecuritytestedandscannedforknownvulnerabilities?)
-   [Q: If I have an issue / feature request item where can I report
    it?](#FrequentlyAskedQuestions-Q:IfIhaveanissue/featurerequestitemwherecanIreportit?)
-   [Q: How do I get the latest version of
    CxFlow?](#FrequentlyAskedQuestions-Q:HowdoIgetthelatestversionofCxFlow?)
-   [Q: How do I (Checkmarx employee) get started with
    CxFlow?](#FrequentlyAskedQuestions-Q:HowdoI(Checkmarxemployee)getstartedwithCxFlow?)

[Process & Workflow](#FrequentlyAskedQuestions-Process&Workflow)

-   [Q: How does CxFlow work with multiple GitHub organizations or
    multiple JIRA
    projects?](#FrequentlyAskedQuestions-Q:HowdoesCxFlowworkwithmultipleGitHuborganizationsormultipleJIRAprojects?)
-   [Q: Can a single yaml file be used to connect to multiple defect
    tracking
    systems?](#FrequentlyAskedQuestions-Q:Canasingleyamlfilebeusedtoconnecttomultipledefecttrackingsystems?)
-   [Q: How do you manage the project creation within CxSAST when
    running CxFlow in Webhook
    mode?](#FrequentlyAskedQuestions-Q:HowdoyoumanagetheprojectcreationwithinCxSASTwhenrunningCxFlowinWebhookmode?)
-   [Q: If a customer uses global webhooks, can they exclude specific
    projects from being
    scanned?](#FrequentlyAskedQuestions-Q:Ifacustomerusesglobalwebhooks,cantheyexcludespecificprojectsfrombeingscanned?)

### Q: Whats is CxFlow?

CxFlow is a solution which enables automated project creation and scan
orchestration and facilitate feedback channels in a closed loop nature.

### Q: Why does this benefit customers?

Enable customers to incorporate Checkmarx into their DevOps/Release
pipelines as early as possible.

### Q: What are some of the CxFlow use cases?

Please refer to the following documentation: [Cx Flow
Workflows](Cx_Flow_Workflows)

### Q: Is CxFlow supported by the product team?

CxFlow is supported by the product team. Tickets can be raised in the
normal workflow. SEG will decide to whom the ticket shall be routed
based on the production matrix and progress.

Actors & Responsibilities can be found here: [Actors &
Responsibilities](https://checkmarx.atlassian.net/wiki/spaces/PTS/pages/1320747488/CxFlow+Operational+Model#CxFlowOperationalModel-Actors&Responsibilities)

Support page:
[Support](https://checkmarx.atlassian.net/wiki/spaces/PTS/pages/1279885323/Support)

### Q: Does CxFlow have Checkmarx Licensing requirements?

No. CxFlow is a tool developed interdependently of the Checkmarx
products and requires no additions to existing customer licenses.

### Q: What Integrations does CxFlow support?

Here is a list of all supported integrations that CxFlow supports,
features of the support and the recommended version customers should
use.

<table>
<thead>
<tr class="header">
<th><p><strong>Software/Services</strong></p></th>
<th><p><strong>Features</strong></p></th>
<th><p><strong>CxFlow Version</strong></p></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p>Jira</p></td>
<td><ul>
<li><p>Issue Tracking</p>
<ul>
<li><p>Custom Bug Types</p></li>
<li><p>Custom transitions in workflows</p></li>
<li><p>Custom fields</p></li>
</ul></li>
</ul></td>
<td><p><code>&gt;= 1.0.0</code></p></td>
</tr>
<tr class="even">
<td><p>GitHub</p></td>
<td><ul>
<li><p>WebHooks</p>
<ul>
<li><p>Pull Requests Scanning and Decorating</p></li>
<li><p>Push events</p></li>
</ul></li>
<li><p>Native Issues Tracker</p></li>
</ul></td>
<td><p><code>&gt;= 1.2.0</code></p></td>
</tr>
<tr class="odd">
<td><p>GitLab</p></td>
<td><ul>
<li><p>WebHooks</p>
<ul>
<li><p>Merge Requests Scanning and Decorating</p></li>
<li><p>Push events</p></li>
</ul></li>
<li><p>Native Issue Tracker</p></li>
</ul></td>
<td><p><code>&gt;= 1.2.0</code></p></td>
</tr>
<tr class="even">
<td><p>Azure DevOps</p></td>
<td><ul>
<li><p>WebHooks</p>
<ul>
<li><p>Merge Requests</p></li>
<li><p>Push events</p></li>
<li><p>Pipelines</p></li>
</ul></li>
<li><p>Work Items</p></li>
</ul></td>
<td><p><code>&gt;= 1.3.0</code></p></td>
</tr>
<tr class="odd">
<td><p>BitBucket</p></td>
<td><ul>
<li><p>WebHooks</p>
<ul>
<li><p>Merge Request Scanning</p></li>
<li><p>Pull events</p></li>
</ul></li>
<li><p>Issue Tracker</p></li>
</ul></td>
<td><p><code>&gt;= 1.4.3</code></p></td>
</tr>
<tr class="even">
<td><p>Rally</p></td>
<td><ul>
<li><p>Issue Tracking</p></li>
</ul></td>
<td><p><code>&gt;= 1.5.3</code></p></td>
</tr>
</tbody>
</table>

### Q: What are the requirements for CxFlow?

Please refer to the following documentation: [Pre-Requisites /
Requirements](Pre-Requisites_Requirements)

### Q: How can I demo CxFlow to a customer?

The Professional Service team have created an easy-to-use [CxFlow Demo
Instance](https://github.com/checkmarx-ts/CxPsPowerHacks/tree/master/CxFlowDemoInstance)
(sub-project of
[CxPsPowerHasks](https://github.com/checkmarx-ts/CxPsPowerHacks)) script
to assist with easy deployment and demonstration of CxFlow.

#### EMEA

CxFlow is installed on the [TS AWS Demo
Instances](https://checkmarx.atlassian.net/wiki/spaces/PTS/pages/671711451/TS+AWS+Demo+instance)
which allows Sales Engineers to demo example use cases to customers.

#### NA

NA SEs should go through the following labs to create their own demo
environments. Please reach out to your direct manager for assistance.

[Workflow Labs](Workflow_Labs_Guides)

#### APAC

*To be defined.*

### Q: Is CxFlow open source?

Yes. The code can be found at the following location:
<https://github.com/checkmarx-ts/cx-flow>

*Note: You may not want to send this to a customer as they might want to
implement CxFlow themselves which 9 times out of 10 has issues (talk to
professional services).*

### Q: Have CxFlow been security tested and scanned for known vulnerabilities?

Yes. CxFlow has undergone multiple different security testing tools and
stages.

For more details, please request from the Product Manager.

### Q: If I have an issue / feature request item where can I report it?

CxFlow Feature Request and Issues should be reported like any other
product feature request, CxFlow will be available as any other Checkmarx
component.

### Q: How do I get the latest version of CxFlow?

You can find the current release of the GitHub releases page:
<https://github.com/checkmarx-ts/cx-flow/releases>

### Q: How do I (Checkmarx employee) get started with CxFlow?

*To be defined.*

## Process & Workflow

### Q: How does CxFlow work with multiple GitHub organizations or multiple JIRA projects?

Overrides can be used at the webhook level & config as code can be added
to the individual repos

[CxFlow
Configuration](CxFlow-Configuration_1276641334.html#CxFlowConfiguration-WebHookURLOverrideParameters-Details(relatedtoabove))
& [Config As Code](Config_As_Code)

### Q: Can a single yaml file be used to connect to multiple defect tracking systems?

Yes - with the limitation of one Jira instance *example to be linked*

### Q: How do you manage the project creation within CxSAST when running CxFlow in Webhook mode?

Overrides can be used to gives projects the same name. Alternatively, a
groovy script can be used to help decide project names and if it should
be scanned. [CxFlow
Configuration](CxFlow-Configuration_1276641334.html#CxFlowConfiguration-ExternalScripting)

### Q: If a customer uses global webhooks, can they exclude specific projects from being scanned?

Yes, this can be performed with overrides & config as code linked above.
