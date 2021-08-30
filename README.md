<div align="center">
  <img align="left" width="80" src="https://drive.google.com/uc?export=view&id=14tcYhY4PgTOkXN-oUasKH8UpUKQcx_cn">
</div>
<h1 align="center" style="color:green; font-size: 300%;" > GeoCode – Virtual Global Treasure Hunt </h1>

<br>
<div align="center">

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=bugs)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=code_smells)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=ncloc)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=alert_status)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)

</div>
<div align="center">

[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=security_rating)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=sqale_index)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=COS301-SE-2021_GeoCode&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=COS301-SE-2021_GeoCode)

</div>

<!---

[![Coverage Status](https://coveralls.io/repos/github/COS301-SE-2021/GeoCode/badge.svg?branch=master)](https://coveralls.io/github/COS301-SE-2021/GeoCode?branch=master)
[![Total Views]](https://hitcounter.pythonanywhere.com/count/tag.svg?url=https://github.com/COS301-SE-2021/GeoCode.svg)
[![Build Status](https://github.com/COS301-SE-2021/GeoCode/workflows/{workflowName}/badge.svg)](https://github.com/COS301-SE-2021/GeoCode/actions)
[![Requirements](https://img.shields.io/requires/github/COS301-SE-2021/GeoCode)](https://img.shields.io/requires/github/COS301-SE-2021/GeoCode)

-->

<h1> Table of Contents</h1>

<div style="margin-left: 4%">

- [Project Overview](#project-overview)
- [GitHub Management](#github-management)
- [Deployment](#deployment)
- [Software Requirements Specification](#software-requirements-specification)
- [Documentation](#documentation)
- [Project Management Tools](#project-management-tools)
- [Demo Video Links](#demo-video-links)
- [Mentors](#mentors)
- [Collaborators](#collaborators)

</div>

# Project Overview

<div style="margin-left: 4%">

With the adjustment to the global pandemic and the modernisation of the world we know, average day <br/> activities, which some have been around for centuries, are needing to find their way onto the virtual world. <br/> Geocaching has started to go out of fashion and is needing a new modern twist on the classic treasure hunt game.

<br/>

There is a need to have GeoCodes as there is a problem of caches going missing from people stumbling upon <br/> the caches without the knowledge of what they are. With GeoCodes the likely hood of a cache going missing is <br/> a lot smaller as it will only be a QR code sticker which people are a lot less likely to remove. A QR code is also a lot <br/> easier to replace. The use of a GeoCode also resolves the issue of logbooks being full and people unable to log <br/> their finds in the books as this will go digital.

<br/>

With the use of GeoCodes as QR codes it also means if someone stumbles upon the QR code and scans it they <br/> will be taken to the GeoCode website where they will be introduced to the concept and have the opportunity to <br/> join the GeoCode community.

</div>

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

# Deployment

<div style="margin-left: 4%">

This will be announced after the first stable release of the GeoCode project.

</div>    

# Software Requirements Specification

<div style="margin-left: 4%">

The following documentation was done in LaTeX and is to document the process followed when designing <br/> and structure of the GeoCode project.

* <a href="https://www.overleaf.com/read/ysgpfdqtrrkw"> GeoCode - SRS Version One </a>
* <a href="https://www.overleaf.com/read/xtrkrnzbmbny"> GeoCode - SRS Version Two </a>
* <a href="https://www.overleaf.com/read/tpdxjjnhsxxq"> GeoCode - SRS Version Three </a>

</div>

# Documentation

<div style="margin-left: 4%">

The following documentation was done in LaTeX and is to document the methods used to create, how <br/> to install and use the GeoCode project.


* <a href="https://www.overleaf.com/read/byjsbnwxcgnm"> Coding Standards </a>
* <a href="https://www.overleaf.com/read/bprjhgpzbcxf">Technical Installation Manual</a>
* <a href="https://www.overleaf.com/read/fjdvrjjsndhv">User Manual</a>
* <a href="https://drive.google.com/file/d/1TPxVUnTDn-eS_OPRwaLPnVOdo1seEFya/view?usp=sharing">Architectural Requirements Version Two</a>
* <a href="https://www.overleaf.com/read/yvhjyygymzdn">Architectural Requirements Version Three</a>
  
The following documentation was done in LaTeX and is to document the usability testing of the project.
* <a href="https://www.overleaf.com/read/hpvhpdhpscwz">Application Theme Test</a>

</div>

# Project Management Tools

<div style="margin-left: 4%">

To aid with the management of the GeoCode project the following tools were used:

* <a href="https://github.com/COS301-SE-2021/GeoCode/projects">GitHub project boards:</a> Task allocations and progress tracking.
* <a href="https://meet.google.com/">Google Meets:</a> Formal team meetings and communication between stakeholders.
* <a href="https://discord.com/brand-newover">Discord:</a> Informal team meetings for quick debriefs, communication.
* <a href="https://discord.com/developers/docs/resources/webhooks">GitHub and Discord Webhooks:</a> Notifications sent to the team's discord server when a commit is made to the GitHub repository.
* <a href="https://www.overleaf.com">Overleaf:</a> LaTeX editor that allows collaboration on creating documentation.

</div>



# Demo Video Links

<div style="margin-left: 4%">

The following links are the live demo videos held on blackboard collaborate to present the different <br/>
phases of the project to the stakeholders of the GeoCode project.

* <a href="https://drive.google.com/file/d/1OqOuoMHSyd4rTUqWW5j_ME1EZcIJHre4/view?usp=sharing"> Demo 1 </a> - :calendar: 2021-06-14
* <a href="https://drive.google.com/file/d/1iSdTMS9bjVXQGxdfrJ-6nl46fX0j3h7n/view?usp=sharing"> Demo 2  </a> - :calendar: 2021-06-21 
* <a href="https://drive.google.com/file/d/1lVIqfENiN10zGNmveXXlzY8A03thT7aV/view?usp=sharing"> Demo 3  </a> - :calendar: 2021-08-20 
* <a href="https://michael-stroh.github.io/capstone/"> Demo 4  </a> - :calendar: 2021-09-24 

</div>

# Mentors

<div style="margin-left: 4%">

The team Peak Performers had the following mentor from <a href="https://5dt.com/"> Fifth Dimension Technologies </a> and the <a href="https://www.up.ac.za/"> University of Pretoria</a>:

Industry Mentor:
  <pre>
    <p style="font-size: 170%;" > Kyle Pretorius </p>
    - kyle.pretorius@5dt.com
    - <a href="https://www.linkedin.com/in/kyle-pretorius-1880a8160/">LinkedIn Account </a>
  </pre>

University Mentor:
  <pre>
    <p style="font-size: 170%;" > Andrew Broekman  </p>
    - andrew.broekman@up.ac.za
    - <a href="https://www.linkedin.com/in/andrewbroekman/">LinkedIn Account</a>
  </pre>

</div>

# Collaborators

<div style="margin-left: 4%">

The following software engineers contributed to the GeoCode project:

<img align="left" width="80" src="https://drive.google.com/uc?export=view&id=1_OiHlqj6YBQIg5iIfT0zQbJZkdsbCjx1">
<h1 style="color:green; font-size: 200%;" > Michael Ströh </h1>
<br/>

   <pre>

            Michael Ströh has learnt throughout his university career that perseverance leads to
            excellence. Through working as a Teaching Assistant for COS 212 (Data Structures)
            and a Student Lab Technician in the CBT labs at the University Of Pretoria he has
            learnt how to manage his time wisely and effectively communicate not only as a team
            but also as an individual. As an Artificial Intelligence project Michael Ströh 
            successfully completed a Sudoku solver using Generic Algorithms and a flower 
            specimens classifier using Machine Learning with back propagation to identify 
            the different species of the iris flower, namely, iris setosa, iris versicolour 
            and iris virginica.
   </pre>

* <a href="https://github.com/Michael-Stroh"> Github Account </a>
* :mortar_board: <a href="https://www.linkedin.com/in/stroh-michael"> LinkedIn Account </a>
* :wave: <a href="https://michael-stroh.github.io/myCV/"> Personal Website </a>
* :email: m.stroh@tuks.co.za
   <details>
       <summary><b> Responsibilities </b></summary>
       <pre>
           - GitHub Readme creation and maintenance.
           - Documentation: Coding Standards
           - Documentation: Software Requirements Specification
           - Documentation: Architecture Requirements and Design Specifications
           - Backend: GeoCode Subsystem and GeoCode Optimization
           - Backend: Events Subsystem
       </pre>
   </details>


<img align="left" width="80" src="https://drive.google.com/uc?export=view&id=1A2cEQ7wslTqTWvZy7f6A4TBXakVAcWcQ">
<h1 style="color:green; font-size: 200%;" > Liam Moore </h1>
<br/>


  <pre>

         Graduated from Heron Bridge College in 2017 with a distinction for both IT and Life
         Orientation. Switched from Computer Engineering to a BSc Information and Knowledge
         Systems with the choice of the Software Development elective group in 2019. I was the
         class representative for INF 154. Since switching to BSc Information and Knowledge
         Systems I have received distinctions for 6 modules. The Software Development elective
         group contained modules that gave me experience with C#, C# entity framework, .NET
         and Node.JS
  </pre>


* <a href="https://github.com/NitronBiohazard"> Github Account</a>
* :mortar_board: <a href="https://www.linkedin.com/in/liam-moore-410004210/"> LinkedIn  Account </a>
* :email: liam.moore@tuks.co.za

   <details>
       <summary><b> Responsibilities </b></summary>
       <pre>
           - Documentation: Software Requirements Specification
           - Documentation: Architecture Requirements and Design Specifications
           - Backend: Collectable Subsystem and the design patterns (Decorator, Factory and Strategy patterns) 
           - Backend: Mission Subsystem
           - Backend: LeaderBoard Subsystem
       </pre>
   </details>


<img align="left" width="80" src="https://drive.google.com/uc?export=view&id=1vofqN3uiCv7RwSIVo-SRCgzG7G2lrOcu">
<h1 style="color:green; font-size: 200%;" > Jenna Gallagher </h1>
<br/>

  <pre>
      
         Graduated from St Dominic’s Priory High School in 2018 with 5 distinctions and 3rd
         in her year. She currently has 17 distinctions out of 23 modules. Jenna has done an
         internship at Propella which helps startups bring their ideas to life through technology.
         There she experienced the life cycle of project development from meeting a client all the
         way through to the end project, this will help her in leading the capstone project.
  </pre>

* <a href="https://github.com/JennaLynGallagher/JennaLynGallagher"> Github Account </a>
* :mortar_board: <a href="www.linkedin.com/in/jenna-gallagher-a79149204"> LinkedIn  Account </a>
* :wave: <a href="https://jennalyngallagher.github.io/"> Personal Website </a>
* :email: jenna.gallagher@tuks.co.za

   <details>
       <summary><b> Responsibilities </b></summary>
       <pre>
           - Documentation: Software Requirements Specification
           - Documentation: Architecture Requirements and Design Specifications
           - Frontend: Explore tabs Pages 
           - Frontend: Events tabs Pages
           - Frontend: Profile tabs Pages
           - Usability Testing: Colour Scheme Test.
       </pre>
   </details>


<img align="left" width="80" src="https://drive.google.com/uc?export=view&id=1QDGS-md4cPuMLbopvBIPRxzjDHnoCbLw">
<h1 style="color:green; font-size: 200%;" > Caleb Johnstone </h1>
<br/>

  <pre>
      
         Caleb Johnstone graduated from Fourways High School in 2018, achieving 4 distinctions
         - the highest of the two being 94% for Mathematics and 96% for Information Technology.
         He placed 7th in Gauteng for Information Technology. Caleb is currently studying in
         his 3rd year for a BSc Information and Knowledge Systems, specialising in Data Science.
         This degree is one of the two degrees offered at the Computer Science department at
         the University Of Pretoria. Caleb was the top Computer Science student for 2019 with
         a weighted average of 90.33%. He was elected as the EBIT House Treasurer for the
         2019/2020 term. He has been the class representative for eight modules.
         He has worked as an Information Technology tutor at UJ in 2018 and he is currently
         working as a test reader at Michael Mount School.
  </pre>

* <a href="https://github.com/CalebJohnstone"> Github Account</a>
* :mortar_board: <a href="https://www.linkedin.com/in/caleb-johnstone-94368a132/"> LinkedIn  Account </a>
* :email: caleb.johnstone@tuks.co.za

   <details>
       <summary><b> Responsibilities </b></summary>
       <pre>
           - Documentation: Software Requirements Specification
           - Documentation: Architecture Requirements and Design Specifications
           - Backend: Collectable Subsystem
           - Backend: User Subsystem  
           - Backend: LeaderBoard Subsystem
           
       </pre>
   </details>


<img align="left" width="80" src="https://drive.google.com/uc?export=view&id=1jeukx2dfvmPY8R6NiL92o8rqUoTZrXb0">
<h1 style="color:green; font-size: 200%;" > Michael Harvey </h1>
<br/>

  <pre>
      
         Michael Harvey graduated from Richards Bay Christian School in 2018 with 4 distinctions,
         and is currently in his 3rd year studying BSc Computer Science. In his first two
         years of university he achieved 21 distinctions in 24 modules, and he ranked as the 
         3rd highest first-year Computer Science student at UP in 2019. On the side he maintains
         various Python projects that make use of the API for the mobile game Clash of Clans: 2
         Discord bots, as well as a program collecting Clan War League participation data from
         hundreds of thousands of clans for statistical purposes.
  </pre>

* <a href="https://github.com/robotic-coder"> Github Account</a>
* :mortar_board: <a href="https://www.linkedin.com/in/michaelharvey-123/"> LinkedIn  Account </a>
* :email: michael.harvey@tuks.co.za

   <details>
       <summary><b> Responsibilities </b></summary>
       <pre>
           - Documentation: Software Requirements Specification
           - Documentation: Architecture Requirements and Design Specifications
           - Documentation: Technical Installation manual
           - Frontend: Collectable tab Pages
           - Frontend: Profile tab Pages
           - Frontend: QR scanning
           - Keycloak integration
           - Server Management and deployment
           
       </pre>
   </details>


</div>
