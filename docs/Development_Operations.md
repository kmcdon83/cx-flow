# Development Operations

Production CxFlow source code will be managed in the following
repository under the enterprise github account (Checkmarx-ltd):

https://github.com/checkmarx-ltd/cx-flow

This repository is linked to Azure DevOps Project for WorkItems and
Pipeline integration (under the CxSDLC project)

<https://dev.azure.com/CxSDLC/CxFlow>

## Code Management / Branching Strategy

Code will be managed according to the GitFlow methodology -
https://datasift.github.io/gitflow/IntroducingGitFlow.html

Branches will be created for Fixes (fix/xxxx), where xxxx is an
identifier that can be used to find the intended bug being fixed
(i.e. Azure DevOps WorkItem \#1234)  

-   Fixes should be derived from the master branch
-   Fixes should be merged into develop as they are introduced to master

Features will be developed according to the following

-   Branch names will be feature/xxxx, where xxxx will be a short name
    describing the enhancement or feature.  Alternatively an internal
    code name can be applied 
-   If a large number of changes and functionality are to be worked on,
    it should be created as a release branch - release-xxxx, where xxxx
    will be a release identifier or name.  Feature branch can then be
    created associated with the release specific branch. 
-   Will be derived from develop branch
-   Continual re-merge of updates from develop should occur to account
    for any fixes applied to production, or any other features that have
    been rolled into the branch

Releases will be derived from develop branch, but will have larger
set/group of features and enhancements 

develop branch will be used as the active code base for developing new
features and the starting point for new releases (if applicable)

-   Features will branch from develop,
-   Fixes will be merged into develop as they are applied to master
-   Automated testing and any quality checks should occur against
    develop branch (push/pull)
-   Field developed contributions (pull) will occur to this branch
-   Formal review should take place to pull requests that occur to this
    branch

master branch will be the production/latest version

-   Code will be introduced through pull requests from develop branch or
    fixes
-   Code will require formal review as it is introduced to master
    through pull requests
-   As code is pushed (after successful merge after pull review and
    approval)

Releases / Tags

-   As code is merged into master branch it should be versioned/tagged
    accordingly and a new release created 
-   Release will be saved on GitHub releases
-   Version increment strategy must be considered for all new versions
    applied.

Product version streams (i.e. 8.x / 9.x SAST) - The intention will be to
support both streams with the same code base.  In the event this is not
possible, the entire workflow can be replicated per stream:

-   8.x-develop \| 9.x-develop (...fix/, release/, etc)

## Field Development Code Management

The github organization Checkmarx-ts has been created as a structured
location for field developed tools and utilities.  It will also be the
structured location for field developed contributions to R&D developed
repositories (CxRepositories/CxRepo/Checkmarx/many more).

https://github.com/checkmarx-ts

-   Originating repository will be forked to Checkmarx-TS
-   Changes will managed according to the above branching strategy
-   Pull Requests will be initiated from Checkmarx-TS to Checkmarx-ltd
-   PSA team will be accountable to the Organization and all changes in
    the field for R&D repositories will be vetted by them first
-   PSA team will be owners of the protected branches (master and
    develop)
-   PSA team will initiate necessary pull requests to R&D repositories
    and will coordinate code reviews 

## Backlog/Feature Management

Development Backlog will be maintained in Azure DevOps 

Backlog / Feature requests will channel through the Product Manager as
per the operating model

Backlog will be prioritized according to a collaborated effort between
Product/Architects

-   Field development contributions to be coordinated across
    Architects/PS Managers
-   VP level approval will be required for escalated requests or
    overrides to our determined priorities

Backlog management and Issue / Defect Management should be managed in
Azure DevOps defect/issues for proper development prioritization

-   Issues can surface through SFDC support, GitHub Issues
    (Checkmarx-ltd and Checkmarx-ts)

All commits should contain Azure DevOps WorkItems that the change is
associated with

## Issue Management / Support

Support will not be given to customers that did not engage PS for their
implementation

-   Best effort basis will be applied in this scenario

As bugs are identified, the issue will be added to the Azure DevOps
WorkItems board

-   Originating source of the issue can be from SFDC support ticket,
    GitHub issue, Azure DevOps
-   Issues will consolidate to one location in Azure DevOps as an Issue

All commits should contain Azure DevOps WorkItems that the change is
associated with

Support will be handled according to the operating model

## **Automated Testing**

All new changes will have a requirement to have unit testing completed
for new components.  A backlog item exists to complete the unit testing
for existing code base.

Automated Integration testing will be be a focus going forward, but be
part of a separate repository of automated test suites.  This is a TBD.

## Release Management

Docker - docker image is published under the checkmarxts github
organization (https://hub.docker.com/r/checkmarxts/cxflow): 

-   *docker pull checkmarxts/cxflow*

GitHub Releases - binaries (jar) will be published to GitHub according
to the release number and release descriptor.

-   https://github.com/checkmarx-ts/cx-flow/releases/tag/

Release descriptions will be maintained in the 

Automated Unit testing must be defined

Automated Integration testing must be defined

-   Checkmarx integration
-   Jira Integration 
-   GitHub / GitLab / BitBucket Integration
