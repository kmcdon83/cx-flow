# Building CxFlow from Source

## Source

Source can be found at the following GitHub repository:

[https://github.com/checkmarx-ts/cx-flow](https://github.com/CxRepositories/cx-flow)

## Compiling

CxFlow uses Gradle for building the applicable jar files.  There are 2
gradle build files:

-   build.gradle → used to build cx-flow-**\<ver\>**.jar for WebHook
    Webservice with JRE 8
-   build-11.gradle → used to build cx-flow-11-**\<ver\>**.jar for
    WebHook Webservice with JRE 11

Note:  JRE 11 requires special build dependency configuration to allow
for JAXB SOAP classes that are only required for the specific Calls to
Checkmarx legacy SOAP 

**Compile**

``` bash
gradlew -b <gradle build file> clean build 
```

There will be a directory structure created (*build/libs/*) where the
jar will be compiled to.

## Pre-built Binary

The latest compiled releases can be found here - look for the latest
release:

<https://github.com/checkmarx-ts/cx-flow/releases>

There are 2 jar files inside the release.zip under the build/libs
folder:

-   cx-flow-**\<ver\>**.jar → used for WebHook Webservice using JRE 8. 
    The entry point contains a tomcat container that launches
-   cx-flow-11-**\<ver\>**.jar → used for WebHook Webservice using JRE
    11.  The entry point contains a tomcat container that launches

## Docker Images

Docker images can be found:

[custodela/cx-flow](https://hub.docker.com/r/custodela/cx-flow)
