# GitHub Management

<div align="center">

[![Build Status](https://img.shields.io/github/issues/COS301-SE-2021/GeoCode.svg)](https://github.com/COS301-SE-2021/GeoCode/issues)
[![Build Status](https://img.shields.io/github/issues-closed/COS301-SE-2021/GeoCode)](https://img.shields.io/github/issues-closed/COS301-SE-2021/GeoCode)
[![Build Status](https://img.shields.io/github/milestones/open/COS301-SE-2021/GeoCode)](https://img.shields.io/github/milestones/open/COS301-SE-2021/GeoCode)

</div>

<div style="margin-left: 4%">

A monorepo with the Git Flow structure will be used in the COS301-SE-2021 organization for the GeoCode project.
<br/>

The following type of branches will be used in the repository:
<br/>
* **master:** The current stable release version of the product. There will only be one of these branches.
* **release/vN.N:** A copy of the master branch for version N.N of the system.
* **development:** A branch consisting of finished code that will be included in the next push to master.
* **dev/SubsystemName:** The main development branch of a single subsystem.
* **dev/SubsystemName-FeatureName:** Used to indicate a new feature being implemented and tested.

 <br/>
      For the management of the project at least two team members will work on a single feature. When a feature has been implemented and tested it will be merged into the develop branch, 
      to merge the feature branch into the development branch a Pull Request will be made where a team member who did not work on the branch being merged will need to be assigned on the 
      branch to review the code and accept the Pull Request or request changes. This is done to ensure the separate subsystems of the project are integrated correctly and help ensure the 
      development branch will contain code and features that work correctly.
<br/><br/>
      The file structure for each of the different subsystem identified in the subsystem will be as such:

    ExampleSubsystem

    |------ controller
               |------ ExampleSubsystemApi
               |------ ExampleSubsystemApiController
    |------ exception
    |------ model
    |------ repository
    |------ request
    |------ response
    |------ service
               |------ ExampleSubsystemService
               |------ ExampleSubsystemServiceImpl
</div>