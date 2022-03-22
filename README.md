# HTTP-log-file-parser

## Requirements
For building and running the application, you need:
- JDK version 11.
- Maven version 3.

## Dependencies
Application uses only standard libraries of Spring Framework. These are:
- spring-boot-starter-web
- spring-boot-starter-test

## Setup the application
The only thing that you should setup before running the application is the log file path.
Firstly, you should download the .gx file of the log file through [this link](https://deepsea-tmp.s3.eu-central-1.amazonaws.com/new_final_final_01.log.gz).
Then, you should unzip it and place it under folder `resources/httpLogFiles`. If folder does not exist, create it. You could also change the property `log.file.path` if you want to place the log file somewhere else.
Also, after starting the application, you should wait for 2-5 seconds as all log lines are parsed on startup and every 10 minutes. This happens in order for the information to be available for generating the statistics.

## Running the application
There are multiple ways to run the application:
- Run main function in Application.java using your IDE.
- Maven command: `mvn spring-boot:run`.

## Endpoints
There are currently 5 endpoints implemented.
- `GET sites/top/{numberOfSites}`: returns the top{numberOfSites} sites requested by the users.
- `GET requests`: returns the percentage of successful or unsuccessful HTTP requests. This endpoint uses a request param `type` that its value is either `successful` or `unsuccessful`.
- `GET hosts/top/{numberOfHosts}`: returns the top{numberOfHosts} hosts with the most requests.
- `GET hosts/top/{numberOfHosts}/sites/top/{numberOfSites}`: returns the top{numberOfHosts} hosts with the most requests as well as the top{numberOfSites} consisting these requests.

## Tests
Test are written using the standard library of Spring Framework. You can selectively run them using your IDE or using the command `mvn clean install`.
Tests thoroughly use Mockito framework abd active profile.

## Quality Check
Implementation has been checked using SonarQube. To startup a SonarQube image locally, you should install docker and run the command `docker run -p9000:9000 sonarqube`.

## Additional links
- https://httpd.apache.org/docs/2.4/logs.html
- https://regexr.com/