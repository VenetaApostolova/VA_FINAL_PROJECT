
<img align="right" src="images/skilloLogo.png" alt="Skilo Academy Logo" />


<div align="center">

# Test automation framework
# VA_FINAL_PROJECT
This is my final project that will be provided for practical examination.
</div>

## TABLE OF CONTENTS

[I. INTRODUCTION](#i-introduction)

 Welcome to I-skillo – your digital space to connect, share, and grow.
More than just a social platform, I-skillo is designed to spark meaningful interaction between users.
Whether you're here to build connections, express yourself, or stay in the loop, we’ve got you covered.

 With I-skillo, you can:

- expand your network by sending and managing connection requests
- keep your personal profile up-to-date and reflective of who you are
- join conversations through posts, comments, and likes
 -discover the latest and most relevant updates curated from your community

 It’s not just about scrolling — it’s about engaging.

[II. QA TEAM MEMBERS](#ii-qa-team-members)

[III. DELIVERABLES](#iii-deliverables)

[IV. USEFUL LINKS](#iv-useful-links)

[V. HOW TO RUN AUTOMATIONS](#v-how-to-run-automations)



## I. INTRODUCTION

__I-skillo__ is a web application that allows you to connect and communicate with other people. It enables you to:

- connect with other Iskilo platform user by managing connection requests;
- manage your profile information;
- form discussions by creating, commenting and liking posts;
- get a feed of the newest/most relevant posts from people in the network.


## II. QA TEAM MEMBERS

### QA Team:
Veneta Apostolova - student

Nikolay Nikolov - supervision

## III. DELIVERABLES
1. [Master Test Plan v3.0]( )
2. Test Cases - [CloudBaseStorage at GoogleDrive]( add link here)
3. [UI Automation With Selenium And Java]( )
 - A full suite of automated UI tests using:
 - Java
 - Selenium WebDriver
 - TestNG
 - Page Object Model
 - Maven

4. [UI Summary Report]( )
 - A brief summary of automated test execution results including:
 - Total test cases run
 - Passed/Failed count
 - Test coverage overview
 - Screenshots/logs for failed tests (if available)
 - Located in the `target/surefire-reports` directory or generated dynamically via IDE/Maven test execution.

## IV. USEFUL LINKS

1. **DEV Project**  
   🔗 [GitHub Repository – VA_FINAL_PROJECT](https://github.com/VenetaApostolova/VA_FINAL_PROJECT)

2. **Bug Tracking System**  
   
3. **Full QA Documentation**  

4. **Manual Test Runs**  

5. **Automation Test Runs**  
   



## V. HOW TO RUN AUTOMATIONS

### __1. Pre-requisites for running automations__

To be able to run the project on localhost and run the test automations, the following programs need to be present on your machine:

- Windows 11 operating system / MAC OS
- OPEN JDK 23
- IntelliJ IDEA Ultimate Edition 2023.1.3 or later
- MAVEN Command Line
- Chrome latest version
- Selenium WebDriver
- Apache Maven 3.8.0 or later

### __2. Running the automations__

__NB:__ Prior to running the project and automations make sure your Chrome driver executables are properly provided in the project.

1. Clone the repo on your computer.
   ```bash
   git clone https://github.com/VenetaApostolova/VA_FINAL_PROJECT.git

2. Open the project in IntelliJ IDEA (Ultimate or Community Edition).

3. Make sure all dependencies are downloaded via Maven

- Right-click on pom.xml → Add as Maven Project,

  - or run the following command in terminal:
  
   ```bash
  mvn clean install
  ```
4. Run the test suite using:
  ```bash
  mvn clean test
```


### __2.1. How to run UI tests automation individually__

1. Open the terminal (in IntelliJ or system terminal).
2. Navigate to the root directory of the project (where the `pom.xml` file is located).
3. Execute the following command:

```bash
mvn clean test
```
