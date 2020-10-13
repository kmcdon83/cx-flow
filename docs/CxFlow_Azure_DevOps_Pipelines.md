# CxFlow Azure DevOps Pipelines

-   [Introduction](#CxFlowAzureDevOpsPipelines-Introduction)
-   [Azure DevOps Pipeline (YAML
    based)](#CxFlowAzureDevOpsPipelines-AzureDevOpsPipeline(YAMLbased))
    -   [Windows Agents](#CxFlowAzureDevOpsPipelines-WindowsAgents)
    -   [Docker
        Containers](#CxFlowAzureDevOpsPipelines-DockerContainers)
-   [Configuration](#CxFlowAzureDevOpsPipelines-Configuration)
    -   [When upgrading to CxSAST \<=
        v9.0:](#CxFlowAzureDevOpsPipelines-WhenupgradingtoCxSAST%3C=v9.0:)
-   [Environment
    Variables](#CxFlowAzureDevOpsPipelines-EnvironmentVariables)
-   [Scripts](#CxFlowAzureDevOpsPipelines-Scripts)
-   [Building](#CxFlowAzureDevOpsPipelines-Building)
    -   [Docker Image](#CxFlowAzureDevOpsPipelines-DockerImage)
        -   [Command Line
            Interface](#CxFlowAzureDevOpsPipelines-CommandLineInterface)
        -   [Build CxFlow using an Azure
            Pipeline](#CxFlowAzureDevOpsPipelines-BuildCxFlowusinganAzurePipeline)

## Introduction

This documentation is to help organisations create and run CxFlow in
Azure DevOps (ADO) Pipelines.

The key features of doing this are:

-   Utilize CxFlow as a Stage/Task in ADO Pipelines

-   Automatically determine matching variables between the Azure
    Pipeline and Checkmarx

    -   Variables can optionally be statically set by the developer team

-   Automatically generating work items from the pipeline if required

-   Cross platform Azure DevOps Agent support

    -   Docker image for cross organisation updating

        -   Updating the image will update all projects configurations

-   Ability to create custom workflows for pipelines to run via the
    endpoint script

    -   Run multi-stage CxFlow jobs

## Azure DevOps Pipeline (YAML based)

Here are different examples of an Azure DevOps Pipeline YAML file that
uses CxFlow to scan the code and create [Work
Items](https://docs.microsoft.com/en-us/azure/devops/boards/work-items/about-work-items?view=azure-devops&tabs=agile-process)
with vulnerabilities. Here is where we invoke CxFlow and a custom
workflow(s) that an organisation might require.

### Windows Agents

The Windows based script is called `entrypoint.ps1` which is the
Powershell script that allows developers to run a wrapper around CxFlow.
This can be distributed to all (security focused) Agents in the
environment along with the `application.yml` and the Java archive of
CxFlow.

**Auto-downloader**

The Powershell script has the ability to download automatically the
current release of CxFlow as a Jar off the [GitHub
Releases](https://github.com/checkmarx-ts/cx-flow/releases). This
feature can be disabled in environments that do not allow out-bound
connections to the internet or downloading of binaries.

``` java
trigger:
- master

pool:
  name: Agents
  vmImage: 'CxAgent'

stages:
- stage: Security
  jobs:
  - job: CxFlow
    steps:
    # This will have to be present on the agent
    - task: PowerShell@2
      inputs:
        # Full or Relative path to Powershell script
        filePath: '.\entrypoint.ps1'
```

### Docker Containers

The docker container version of CxFlow runs the exact same code as the
Linux based Agents do. The only primary difference is that you can
create a Docker image (container all the code and configuration) in a
single binary which is immutable and can be distributed by using Docker
Registries.

``` java
pool:
  vmImage: 'ubuntu-latest'

stages:
- stage: Security
  jobs:
  - job: CxFlow
    steps:
    # This step might not be needed if Docker is pre installed
    - task: DockerInstaller@0
      inputs:
        dockerVersion: '17.09.0-ce'
    # Run CxFlow
    - bash: docker run -v `pwd`:/code --rm --env-file <(env) organisation/cxflow-azure:latest
      env:
        # Required Settings
        AZURE_ACCESS_TOKEN: $(System.AccessToken)
        CHECKMARX_PASSWORD: $(CHECKMARX_PASSWORD)
```

## Configuration

The `application.yml` is where most of the static settings are stored
that do not change. These can be configured per organisation and nothing
sensitive should be stored in this file unless encrypted (encrypt them
using [Jasypt](http://www.jasypt.org/)).

``` java
# ...
checkmarx:
  username: ${CHECKMARX_USER}
  password: ${CHECKMARX_PASSWORD}
  client-secret: 014DF517-39D1-4453-B7B3-9930C563627C
  base-url: ${CHECKMARX_URI}
  multi-tenant: false
  scan-preset: ${CHECKMARX_PRESET:Checkmarx Default}
  configuration: Default Configuration
  team: ${CHECKMARX_TEAM:\CxServer\SP\Company}
  preserve-xml: true
  incremental: false

azure:
  token: ${AZURE_ACCESS_TOKEN}
  url: ${AZURE_URL}
  api-version: 5.0
  issue-type: issue
  closed-status: Closed
  open-status: Active
  false-positive-label: false-positive
  block-merge: true
```

### When upgrading to CxSAST \<= v9.0:

When updating to CxSAST version 9.0 or above, the REST API changes so
CxFlow needs to swap to version 9.0 support and some configuration
changes need to be done. This requires the following changes:

``` java
# ...
checkmarx:
  version: 9.0  
  client-id: resource_owner_client
  client-secret: 014DF517-39D1-4453-B7B3-9930C563627C
  scope: access_control_api sast_rest_api
  # ...
  team: /CxServer/Checkmarx/CxFlow
```

The Team syntax changes from version 8.9 to 9.0. Originally back-slashes
are now forward-slashes.

A full version example can be found
[here](https://github.com/checkmarx-ts/cx-flow/blob/develop/src/main/resources/application-9.0.yml).

## Environment Variables

Here is a list the different variables that can be passed into the
Docker environment or the `endpoint.sh` script.

<table>
<thead>
<tr class="header">
<th><p><strong>Name</strong></p></th>
<th><p><strong>Required?</strong></p></th>
<th><p><strong>Description</strong></p></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><code>AZURE_ACCESS_TOKEN</code></p></td>
<td><p><strong>Yes</strong></p></td>
<td><p>This is the token that is used to clone the repository and open/edit/close Work Items.</p>
<p>You can use the Azure <code>System.AccessToken</code></p></td>
</tr>
<tr class="even">
<td><p><code>AZURE_URL</code></p></td>
<td><p><strong>No</strong></p></td>
<td><p>This is the URL to the organisation in Azure. Default is <code>System.TeamFoundationCollectionUri</code></p></td>
</tr>
<tr class="odd">
<td><p><code>CHECKMARX_URI</code></p></td>
<td><p><strong>Yes</strong></p></td>
<td><p>The URL/URI of where Checkmarx is hosted.</p>
<p>This can be built in by default by editing the <code>application.yml</code>.</p></td>
</tr>
<tr class="even">
<td><p><code>CHECKMARX_USERNAME</code></p></td>
<td><p><strong>Yes</strong></p></td>
<td><p>Username of the Checkmarx user (typically a service scanner account)</p></td>
</tr>
<tr class="odd">
<td><p><code>CHECKMARX_PASSWORD</code></p></td>
<td><p><strong>Yes</strong></p></td>
<td><p>Password for the Checkmarx user.</p>
<p>This should be a <a href="https://docs.microsoft.com/en-us/azure/devops/pipelines/process/variables?view=azure-devops&amp;tabs=yaml%2Cbatch#secret-variables">Azure Pipeline Secrets</a> or encrypted using <a href="http://www.jasypt.org/">Jasypt</a> (see <code>CXFLOW_KEY</code> section).</p></td>
</tr>
<tr class="even">
<td><p><code>CHECKMARX_PROJECT</code></p></td>
<td><p><strong>No</strong></p></td>
<td><p>Project name in Checkmarx. The Default is the Azure Project name (Build.Repository.Name).</p>
<p>This can work along side the <code>project-script</code> feature of CxFlow.</p></td>
</tr>
<tr class="odd">
<td><p><code>CHECKMARX_TEAM</code></p></td>
<td><p><strong>No</strong></p></td>
<td><p>Project Team that the project should be under. Default <code>\CxServer\SP\Company</code>.</p>
<p>This can be built in by default by editing the <code>application.yml</code>.</p>
<p>This can work along side the <code>team-script</code> feature of CxFlow.</p></td>
</tr>
<tr class="even">
<td><p><code>CHECKMARX_PRESET</code></p></td>
<td><p><strong>No</strong></p></td>
<td><p>Project Preset to use. Default is <code>Checkmarx Default</code></p></td>
</tr>
<tr class="odd">
<td><p><code>CXFLOW_KEY</code></p></td>
<td><p><strong>No</strong></p></td>
<td><p>This key is used for decryption of the tokens or sensitive data using Jasypt. By Default, the application will not decrpt anything.</p></td>
</tr>
<tr class="even">
<td><p><code>CXFLOW_KEY_ALGORITHM</code></p></td>
<td><p><strong>No</strong></p></td>
<td><p>Custom algorithm you want to use with Jasypt. The default value is <code>PBEWITHHMACSHA512ANDAES_256</code>.</p></td>
</tr>
</tbody>
</table>

## Scripts

These scripts are used on an Azure DevOps Agent as part of a Pipeline.
They provide a wrapper around CxFlow to automatically pull out various
[built-in Azure Pipeline
variables](https://docs.microsoft.com/en-us/azure/devops/pipelines/build/variables?view=azure-devops&tabs=yaml)
to provide a seamless experience for organisations. Many of the
variables are dictated based on environment variables passed into the
Docker container at run time or the `application.yml`.

These can be updated to your requirements and can be different from
organisation-to-organisation.

The `entrypoint.sh` script is to support both Linux based agents and
it’s the entry point for the Docker image.

## Building

### Docker Image

We recommend that organisations create a git repository of these files
to track changes and easily deploy the images for all pipelines in the
organisation in a private registry.

*Note: This Docker image can be used for any pipelines as long as the
ADO variables being supplied are updated to corresponding build
systems/bug tracking systems.*

#### Command Line Interface

In the working directory of the source code, run the following commands:

``` bash
# Building the Docker image
docker build -t organisation/cxflow .

# Pushing image to registry
docker push private-registry:5000/organisation/cxflow
```

Feel free to change the name of the image to anything but make sure that
the pipelines match the container name.

#### Build CxFlow using an Azure Pipeline

If you have created a separate repository in Azure DevOps and use this
simple pipeline to build and push the Docker image into an internal
registry. This allows for organisations to automatically make updates to
CxFlow, commit the changes, build the Docker container and push them to
a globally accessible directory.

``` java
# This Azure Pipeline is for building Docker images using Azure
pool:
  vmImage: 'ubuntu-latest'

variables:
  imageName: 'organisation/cxflow-azure'

steps:
- task: Docker@2
  displayName: Login
  inputs:
    command: login
    containerRegistry: dockerRegistryServiceConnection1
- task: Docker@2
  displayName: Build and Push Image
  inputs:
    repository: $(imageName)
    command: 'buildAndPush'
    Dockerfile: '**/Dockerfile'
```
