# CxFlow Automated Code Profiling

Config as Code

Only implemented for GitHub and GitLab currently for WebHook execution.

  

-   Registration of an Organizational WebHook across all Organizations
    pointed to CxFlow
    -   Registration of the webhook will be for push events
-   CxFlow will be configured to process events associated with branches
    deemed important/protected across the enterprise
    -   master
    -   develop
    -   release\*
    -   etc (TBD)
-   Upon receiving an event, CxFlow will:
    -   Check local cache to see if the given repository has been
        previously profiled, regardless of branch
    -   If yes, use the already established preset according to the
        cached value
        -   Cache will leverage a Checkmarx custom field stored within
            the project
    -   If no, CxFlow will profile the application by using the Contents
        GitHub API
        -   Global exclusions will be applied to the profiling
            (paths/naming patterns)
        -   File type, number of references, percentage of code base
            (reflected from post exclusions) will be mapped
        -   CxFlow will iterate through a rule set that will attempt to
            match the fingerprint of the source code
            -   Rules will be evaluated in order provided in the
                configuration file, once a match is found it will stop
                checking further
            -   There will be a default / catchall rule for those not
                matching a finger print
        -   The based on the fingerprint rule matching, an associated
            Checkmarx preset will be assigned
        -   This information will be save to the local CxFlow cache. 
            *Note:  If multiple instances of CxFlow are load balanced,
            the cache will only be available to the local instance doing
            the processing.  This design can be enhanced if required.*
    -   A scan request for the repository will be initiated with the
        scan preset that has been assigned in the previous step(s)
        -   Scans will be attempted as incremental with the following
            rules:
            -   A full scan was conducted within the last 7 days
            -   A scan was conducted within the last 5 scans
        -   Global file exclusion pattern(s) will be applied for every
            scan according to the CxFlow configuration
    -   Optionally Result feedback can be configured 
        -   CxFlow will generate the XML report
        -   Results will be filtered
        -   Results will be published according to the configured
            feedback channel(s)

## Automated Profile Configuration

Default file is CxProfile.json unless provided as an override in the
configuration yaml for CxFlow

The configuration is evaluated in order found within the file, and upon
meeting the first match based on the criteria, the preset is selected. 
The default entry should be last, otherwise it will be selected as soon
as it is reached.

| Key           | Description                                                                                                                           |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------|
| name          | Identifier for the profile.  If Default is the name, the rules are not evaluated and the preset is selected as this entry is reached. |
| preset        | The Checkmarx scan preset that is associated with the profile                                                                         |
| files         | List of regular expressions that match file/path patterns.  All references in the list must be found for a match                      |
| weight        | List of weighting criteria based on language percentages that must all match for the profile to be selected                           |
| weight→type   | This is the associated language name based on the information from the repository (i.e. Java, C\#, ASP, HTML, CSS)                    |
| weight→weight | The minimum percentage of code required for a match                                                                                   |

All criteria must be met (file matches, weighting matches) to match and
select the given profile

  

  

## Attachments:

<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~Untitled
Diagram.drawio.tmp](attachments/1345586126/1345389627.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345356862.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345946042.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345913187.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345389633.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345847786.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345880327.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345847795.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345913196.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345913205.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345618997.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345814843.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345389642.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345782034.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345586130.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345586139.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345586148.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345651005.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345946051.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345847804.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345978703.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345880336.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345814852.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345356871.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345880345.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345356880.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345651014.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345847813.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345586157.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345684009.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345946060.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345946069.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345684018.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345946078.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345814861.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345684027.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto
Profile.tmp](attachments/1345586126/1345978698.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile](attachments/1345586126/1346273307)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile.png](attachments/1345586126/1346273317.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Sequence.tmp](attachments/1345586126/1345389663.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Sequence.tmp](attachments/1345586126/1345946087.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq.tmp](attachments/1345586126/1345913222.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq.tmp](attachments/1345586126/1345586172.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619006.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389672.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345586177.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345586186.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946092.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345978712.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345847822.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345782043.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619015.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389681.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345586195.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345847831.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345651023.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356893.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345586204.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1346011558.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345586213.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684036.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345651032.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946101.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356902.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345782052.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946110.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1346011573.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913231.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345651047.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1346011582.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913240.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389693.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389702.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619028.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619037.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814873.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389717.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913249.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345978721.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389726.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389735.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619046.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356911.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389744.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946119.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684048.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814882.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814891.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345651059.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345978730.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619055.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684057.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946128.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684066.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684075.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356920.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619064.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619073.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356929.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619082.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814900.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913258.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356938.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389753.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684084.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345978739.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946137.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913267.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356947.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684093.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619091.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345978748.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356956.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345356965.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684102.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814909.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684111.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913276.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946146.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345651068.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913285.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684120.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619100.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345978763.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684129.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814918.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345651077.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946155.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389762.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345619109.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814927.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684138.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile Seq1](attachments/1345586126/1345913368)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814936.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile Seq1.png](attachments/1345586126/1345684252.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~drawio\~5bdb188e216ccf49622e5cd2\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345880354.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946185.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389798.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814975.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684203.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814984.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946198.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389807.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684212.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389816.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345814993.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345815002.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345815011.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684221.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389825.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389834.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345389843.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913341.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345946211.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913350.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913359.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684230.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345684239.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345815020.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile Seq1](attachments/1345586126/1345389771)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile Seq1.png](attachments/1345586126/1345651086.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile
Seq1.tmp](attachments/1345586126/1345913336.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile.tmp](attachments/1345586126/1346338866.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile](attachments/1345586126/1346338885)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile.png](attachments/1345586126/1346273349.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile.tmp](attachments/1345586126/1346306055.tmp)
(application/octet-stream)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile.tmp](attachments/1345586126/1346207749.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile.tmp](attachments/1345586126/1346338875.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile.tmp](attachments/1345586126/1346207759.tmp)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile](attachments/1345586126/1345913214)
(application/vnd.jgraph.mxfile)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" /> [CxFlow
GH Auto Profile.png](attachments/1345586126/1345586166.png)
(image/png)  
<img src="images/icons/bullet_blue.gif" width="8" height="8" />
[\~CxFlow GH Auto Profile.tmp](attachments/1345586126/1346273302.tmp)
(application/octet-stream)  
