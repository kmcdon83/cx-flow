# Running CxFlow as a Windows Service

CxFlow can be run as a Windows Service using the Windows Service Wrapper
(winsw). Here's a step-by-step guide on how to run CxFlow as a Windows
Service.

# Setup

  

**Step 1  
**Download the Windows Service Wrapper from GitHub
: https://github.com/kohsuke/winsw/releases

There are two executables available for download. Make sure you use the
version corresponding to the .NET libraries installed in your server.

**Step 2**

Rename the WinSW.NET.x.exe to CxFlow.exe

**Step 3**

Move the CxFlow.exe to the directory where the CxFlow executable jar
resides.

**Step 4**

Create an XML configuration file for the Windows Service Wrapper

**Example Configuration XML**

``` java
<?xml version="1.0" encoding="UTF-8"?>
<service>
    <id>CxFlow</id>
    <name>CxFlow</name>
    <description>CxFlow Windows Service</description>
    <executable>java</executable>
    <arguments>-jar "cx-flow-1.X.jar" --spring.config.location=/path/to/application.yml</arguments>
    <logmode>rotate</logmode>
</service>
```

**Running CxFlow as a specific service**

If CxFlow needs to run as a specific service account, include the
following section in the above XML configuration file.

**Example service account**

``` java
  <serviceaccount>
        <domain>NT AUTHORITY</domain>
        <user>NetworkService</user>
    </serviceaccount>
```

  

# **Installing, Starting, Stopping, Uninstalling**

Execute CxFlow.exe \<operation\>

where operation can be:

-   `install` to install the service to Windows Service Controller. 
-   `uninstall` to uninstall the service. The opposite operation of
    above.
-   `start` to start the service. The service must have already been
    installed.
-   `stop` to stop the service.
-   `restart` to restart the service. If the service is not currently
    running, this command acts like `start`.
-   `status` to check the current status of the service.
    -   This command prints one line to the console.
        -   `NonExistent` indicates the service is not currently
            installed
        -   `Started` to indicate the service is currently running
        -   `Stopped` to indicate that the service is installed but not
            currently running.
