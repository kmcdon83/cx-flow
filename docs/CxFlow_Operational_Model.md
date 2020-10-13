# CxFlow Operational Model

# Implementations

All implementations should involve PS.  During PS engagement(s) the
following should be completed

-   Provide DevOps/Development and secure code scanning consulting
-   Map out use cases for
    -   Scanning approach
    -   Feedback channels / results consumption
-   Assess if/how CxFlow can be leveraged to meet their desired use case
-   Based on the desired use case, PS should assist with the setup to
    ensure it is done correctly
-   Support will be given to customers that choose to leverage PS for
    their implementation/setup

# Features/Enhancements

## Workflow

-   Clients will engage in discussions around enhancements to CxFlow
    through 
    -   Discussions with RSM or SE
    -   PS/Solution Architecture discussion or engagement
    -   TAMs and CSM leads
    -   Technical Leads
-   SE and/or PS will assess the validity of the use case
    -   If the request is considered a valid scenario to enhance CxFlow,
        then the request must be logged in ***Saleforce as a Feature
        request*** and should be passed to the *Product Manager*
    -   If the request does not seem to be a valid use case for
        enhancing CxFlow, then an alternative approach/solution should
        be proposed to the client
        -   If unsure, discuss with Architect
-   Product Manager will have the final say if the enhancement will be
    added to the backlog, and if so can be prioritized.
-   Product Manager discuss architectural feasibility with Architect,
    and establish a design/approach.
-   Work Items will be logged into [Azure
    DevOps](https://dev.azure.com/CxSDLC/CxFlow/_workitems/) 
-   Based on priority of the request, R&D will size the efforts and
    allocate resources to complete the work
    -   In the event no resources are available to complete changes
        within a reasonable time, PS can be used to augment development
        activities
    -   If PS is used to complete development activities, the work
        efforts will be coordinated with *PS Manager(s)* and *Architect*
    -   Field development contributions will follow this process
-   Once development is completed, testing should be conducted and a
    release should packaged and published to GitHub releases
    -   Release notes and internal documentation should be updated to
        reflect necessary changed/configuration elements
-   Client can be notified of the new release with their enhancement
    along with any necessary instructions

***NOTE:**  This operating model should function within a customers
region (i.e. North American SE/PS/SA will handle North American
clients)*

# Support - Phase 1 \[Interim\]

## Workflow

-   Clients will raise a support ticket through normal channels.
    -   A new CxFlow support category should be created
-   If the issue is easy to fix or is part of the common / known
    problems, then support (tier 2) can resolve directly
-   Tier 3 will be handled by the ***regional PS*** team.  Tier 2 will
    need to route the issue accordingly
-   PS will debug/troubleshoot issues with the client and attempt to
    resolve
-   Tier 4 escalation will be to an Architect
-   If a bug is suspected or identified, the development process should
    be invoked for the fix/changes
-   Solution or new release should be provided to customer as soon as it
    can be available

*NOTE: Most inquiries will be due to misconfiguration/misunderstanding -
these do not require review from Architect for the proposed solutions.*

  

# Support - Phase 2 \[End State\]

## Workflow

The steady-state process should exclude PS and Architect from Tier 3 and
4 support.  This will be transitioned to SEG and R&D accordingly.

-   Clients will raise a support ticket through normal channels.
    -   A new CxFlow support category should be created
-   If the issue is easy to fix or is part of the common / known
    problems, then support (Tier 3) can resolve directly
-   Tier 4 will be handled by the **SEG** team.  
-   SEG will debug/troubleshoot issues with the client and attempt to
    resolve
-   Final escalation will be to R&D
-   If a bug is identified, the development process should be invoked
    for the fix
-   Solution or new release should be provided to customer as soon as it
    can be provided

# Actors & Responsibilities

<table>
<thead>
<tr class="header">
<th>Role</th>
<th>Responsibility</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>RSM</td>
<td><ul>
<li>Speak at a high level the capability that CxFlow can offer to aid in integrating Checkmarx into many DevOps and Development practices</li>
<li>Set expectations with the client around implementation and support </li>
</ul></td>
</tr>
<tr class="even">
<td>SE</td>
<td><ul>
<li>Speak at a detailed level the capability that CxFlow can introduce and how it can integrate DevOps and Development practices with Checkmarx.  </li>
<li>Understand customer needs and assess if CxFlow is right for them. </li>
<li>Understand if what customer requires can be done with CxFlow, and if not, assess the validity of enhancing CxFlow to meet their needs</li>
<li>Work with PS on any of the above to ensure use cases and capabilities are aligned with the solution</li>
<li>Set expectations with the client around implementation and support </li>
</ul></td>
</tr>
<tr class="odd">
<td>PSE</td>
<td><ul>
<li>Provide development augmentation/support for enhancements</li>
<li>Speak to capabilities at a detailed level</li>
<li>Perform implementation engagements</li>
<li>Act as Tier 3 support</li>
</ul></td>
</tr>
<tr class="even">
<td>Architect</td>
<td><ul>
<li>Provide Architectural oversight</li>
<li>Provide input into backlog and client request prioritization </li>
<li>Perform implementation consulting (best practices, architecture and integration discussions)</li>
<li>Perform development activities as required</li>
<li>Act as Tier 4 support (Interim)</li>
<li>Work closely with R&amp;D to manage code from field development</li>
</ul></td>
</tr>
<tr class="odd">
<td>Support</td>
<td><ul>
<li>Understand basic / common troubleshooting techniques</li>
<li>Route issues to appropriate PS team
<ul>
<li>NA Clients → NA PS</li>
<li>EMEA → EMEA PS</li>
</ul></li>
</ul></td>
</tr>
</tbody>
</table>

  

# Tracking Customer Use Cases

Customers and their use cases are being maintained here

(function(){ var data = { "addon\_key":"cello",
"uniqueKey":"cello\_\_trello-card2815483287604556974",
"key":"trello-card", "moduleType":"dynamicContentMacros",
"moduleLocation":"content", "cp":"/wiki", "general":"", "w":"314px",
"h":"218px",
"url":"https://cello.atlassian.io/trello-card.html?url=https%3A%2F%2Ftrello.com%2Fb%2FegECux19%2Fts-automation-tracking&compact=&xdm\_e=https%3A%2F%2Fcheckmarx.atlassian.net&xdm\_c=channel-cello\_\_trello-card2815483287604556974&cp=%2Fwiki&xdm\_deprecated\_addon\_key\_do\_not\_use=cello&lic=none&cv=1.676.0&jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1NTcwNTg6NjY0NTgyYWMtMmNjOS00MGRjLTgxN2EtYmUyOGM2NWU3YjdkIiwicXNoIjoiZmI0MGE4YTNiMDQzYTRmMGYyOWI4OTQyMjQ1NTY0YzUzYzBiMTA3NGUyMjA4YzJjNGE0MjVhOGQ2NzZlMTI3YSIsImlzcyI6IkNvbmZsdWVuY2U6OTM5MjUxNjkxNyIsImNvbnRleHQiOnt9LCJleHAiOjE1ODkyMjkxNjksImlhdCI6MTU4OTIyODk4OX0.Lh1zNuS1CYTrcSb7sNXwQqSeHXciiIqReACYRlQifQY",
"contextJwt":
"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1NTcwNTg6NjY0NTgyYWMtMmNjOS00MGRjLTgxN2EtYmUyOGM2NWU3YjdkIiwiaXNzIjoiQ29uZmx1ZW5jZTo5MzkyNTE2OTE3IiwiY29udGV4dCI6eyJjb25mbHVlbmNlIjp7Im1hY3JvIjp7Im91dHB1dFR5cGUiOiJodG1sX2V4cG9ydCIsImhhc2giOiJlYzllM2EwMS1mN2ViLTQ4OGYtODRhZS03NmMwOGQ0MzIwNTMiLCJpZCI6ImVjOWUzYTAxLWY3ZWItNDg4Zi04NGFlLTc2YzA4ZDQzMjA1MyJ9LCJjb250ZW50Ijp7InR5cGUiOiJwYWdlIiwidmVyc2lvbiI6IjciLCJpZCI6IjEzMjA3NDc0ODgifSwic3BhY2UiOnsia2V5IjoiUFRTIiwiaWQiOiIxMjAyNTg1NyJ9fX0sImV4cCI6MTU4OTIyOTg4OSwiaWF0IjoxNTg5MjI4OTg5fQ.j0kds\_z9mvMiwKyyu8a4dr1YieuNyMkbfvo-onJwKuc",
"structuredContext":
"{\\"confluence\\":{\\"macro\\":{\\"outputType\\":\\"html\_export\\",\\"hash\\":\\"ec9e3a01-f7eb-488f-84ae-76c08d432053\\",\\"id\\":\\"ec9e3a01-f7eb-488f-84ae-76c08d432053\\"},\\"content\\":{\\"type\\":\\"page\\",\\"version\\":\\"7\\",\\"id\\":\\"1320747488\\"},\\"space\\":{\\"key\\":\\"PTS\\",\\"id\\":\\"12025857\\"}}}",
"contentClassifier":"content",
"productCtx":"{\\"page.id\\":\\"1320747488\\",\\"macro.hash\\":\\"ec9e3a01-f7eb-488f-84ae-76c08d432053\\",\\"space.key\\":\\"PTS\\",\\"user.id\\":\\"557058:664582ac-2cc9-40dc-817a-be28c65e7b7d\\",\\"page.type\\":\\"page\\",\\"content.version\\":\\"7\\",\\"page.title\\":\\"CxFlow
Operational Model\\",\\"macro.body\\":\\"\\",\\": = \| RAW \| =
:\\":\\"url=https://trello.com/b/egECux19/ts-automation-tracking\\",\\"space.id\\":\\"12025857\\",\\"macro.truncated\\":\\"false\\",\\"content.type\\":\\"page\\",\\"output.type\\":\\"html\_export\\",\\"url\\":\\"https://trello.com/b/egECux19/ts-automation-tracking\\",\\"page.version\\":\\"7\\",\\"user.key\\":\\"ff8080815a631398015a6c4888ef0000\\",\\"content.id\\":\\"1320747488\\",\\"macro.id\\":\\"ec9e3a01-f7eb-488f-84ae-76c08d432053\\"}",
"timeZone":"America/New\_York", "origin":"https://cello.atlassian.io",
"hostOrigin":"https://checkmarx.atlassian.net",
"sandbox":"allow-downloads allow-forms allow-modals allow-popups
allow-scripts allow-same-origin
allow-top-navigation-by-user-activation", "apiMigrations": { "gdpr":
true } }; if(window.AP && window.AP.subCreate) {
window.\_AP.appendConnectAddon(data); } else { require(\['ac/create'\],
function(create){ create.appendConnectAddon(data); }); } }());

  

## Gaps

-   RSM/SE Global Enablement - how to position the solution
-   Global PS Enablement - how to implement all of the use cases within
    a client site \| how to develop / add capabiliies
-   Support Enablement
-   Expectation setting with client around implementation and support

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256297.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320158500.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320616491.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320747492.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256306.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320158509.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256315.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320158518.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320158527.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256324.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320158536.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256333.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320747501.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320158545.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320158554.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256342.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256351.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320616500.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256360.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320616509.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320747510.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320747519.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow
Operation.tmp](attachments/1320747488/1320256291.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation](attachments/1320747488/1321828497)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation.png](attachments/1320747488/1321697378.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321238533.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321435137.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321304069.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271301.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321467905.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664646.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321762882.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566497.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271475.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566506.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321697342.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321828449.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468089.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321631859.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730110.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321861152.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730119.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730128.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321828458.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795684.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321828467.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795693.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321599165.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468098.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664655.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321828476.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321828485.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271487.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321697351.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321631868.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664664.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795702.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664673.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321599174.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664682.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730140.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468107.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468116.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566515.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271496.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271505.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566524.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500830.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321697360.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321861161.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321631877.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566533.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500839.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271514.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730149.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500848.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321599183.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664694.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321697369.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321631886.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566542.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321599195.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321369605.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation](attachments/1320747488/1324647001)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation.png](attachments/1320747488/1325039959.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321631895.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500860.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566551.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321599204.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321861170.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730161.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566560.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468172.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795774.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500916.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271604.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468181.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500925.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468190.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321762943.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271613.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730204.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500934.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321271622.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664747.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730213.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468202.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730222.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500949.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664756.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500965.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566603.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664768.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321599242.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795793.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664777.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321468211.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795802.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566615.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500974.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795811.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321500983.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321697485.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795821.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321631939.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321795844.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321599251.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664788.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321697497.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321697506.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321730240.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321566624.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1321664703.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support](attachments/1320747488/1325039798)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support.png](attachments/1320747488/1324941581.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324941572.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324679603.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324843173.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324679612.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324646739.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324646748.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324843182.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324843191.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324679598.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support](attachments/1320747488/1321500992)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support.png](attachments/1320747488/1321500997.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325105272.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325039932.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325072545.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325105281.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325039941.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325105290.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325301899.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1324646992.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325039950.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325138015.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation](attachments/1320747488/1325039982)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation.png](attachments/1320747488/1325138027.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325039972.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325105306.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1324974616.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1324647011.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325170828.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325072558.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation](attachments/1320747488/1320158563)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Operation.png](attachments/1320747488/1320747528.png) (image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1561888790.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325301908.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324974626.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325138044.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325105319.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324647040.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1324647049.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325072574.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325138053.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325072583.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325170843.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future](attachments/1320747488/1325105328)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future.png](attachments/1320747488/1325138065.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support.tmp](attachments/1320747488/1325170838.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325138075.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future](attachments/1320747488/1325170901)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future.png](attachments/1320747488/1325105378.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324647071.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325072595.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324974638.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325072605.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324974648.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325138084.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325040005.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325105338.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325105348.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325040015.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324647081.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325301922.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325138094.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325301932.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325040025.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324974658.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325105358.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1](attachments/1320747488/1325301956)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1.png](attachments/1320747488/1324974676.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325301969.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1325040043.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1](attachments/1320747488/1325170881)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1325170872.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1.png](attachments/1320747488/1324974686.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1325105368.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1325072615.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1](attachments/1320747488/1325072625)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1.png](attachments/1320747488/1324647162.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1325170922.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325170891.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325301979.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325301989.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325138115.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324647124.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324647134.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1325170911.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future](attachments/1320747488/1324647058)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future.png](attachments/1320747488/1325301917.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future.tmp](attachments/1320747488/1324647066.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1324974717.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1325105398.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1](attachments/1320747488/1325301942)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
Support - Future-1.png](attachments/1320747488/1325040035.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Support - Future-1.tmp](attachments/1320747488/1324974671.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1561888801.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1561921348.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow Operation.tmp](attachments/1320747488/1325105267.tmp)
(application/octet-stream)  
