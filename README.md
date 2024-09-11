# bug-tracker
Hello, and welcome to the GoHealth Bug Tracker API. This project consists of two main parts. Here, we'll go over how to run them together.

1. Spring Boot Application 
   -
      This application is mainly in Java 17 using the Spring Boot framework, and ran in gradle. 
      - After downloading, simply navigate in your terminal to the main project folder (gohealth-bug-tracker)
      - Please be sure that you have Java installed by running "java -version"
        - if you do not have java installed, please install it here (https://www.java.com/en/download/help/download_options.html)
      - Since the gradlew file is already present in this project, you are not required to download gradle (although if you want to, you can do so here: https://gradle.org/install/)
      - At the home directory of the project, run "./gradlew bootRun" in your terminal
2. Docker
   -
    - This project can also be run via docker. Upon compilation of the project a snapshot image of the project can be found at gohealth-bug-tracker/build/libs/gohealth-bug-tracker-0.0.1-SNAPSHOT.war, and can be run via "docker run docker.io/library/gohealth-bug-tracker:0.0.1-SNAPSHOT"
    - If the image is not there it can be created by running "./gradlew bootBuildImage --imageName=springio/gs-spring-boot-docker"
    - Then, run docker run docker.io/library/gohealth-bug-tracker:0.0.1-SNAPSHOT
    - Alternatively, an image can also be created from the 'dockerfile' included in the project root 
3. Java Command Line Interface
   -
   - To use the command line interface, first navigate to the root of the project
   - Run "javac JavaCLI.java" to compile the program
   - Next, run "java JavaCLI" 