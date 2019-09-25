[![Build Status](https://prd-tcp-jenkins.excellalabs.com/buildStatus/icon?job=TCP%2FJava%2Ftcp-java%2Fmaster)](https://prd-tcp-jenkins.excellalabs.com/job/TCP/job/Java/job/tcp-java/job/master/)
[![Coverage](http://sonarqube.excellalabs.com:9000/api/project_badges/measure?project=tcp-java&metric=coverage)](http://sonarqube.excellalabs.com:9000/dashboard?id=tcp-java)
[![Vulnerabilities](http://sonarqube.excellalabs.com:9000/api/project_badges/measure?project=tcp-java&metric=vulnerabilities)](http://sonarqube.excellalabs.com:9000/dashboard?id=tcp-java)


# tcp-java repository

A CRUD example using Spring WebFlux and Java 11


##### 1. Install:
  - [docker](https://docs.docker.com/engine/installation/)
  - [docker-compose](https://docs.docker.com/compose/install/)
  - keytool: this is part of the JDK; if you don't already have it: `sudo apt-get install openjdk-11-jdk`
      -For mac run: `brew cask install java`

Docker (Engine) version 18.09.8 is known to work;
docker-compose version 1.24.1 is known to work; docker-compose version 1.17.1 is known to NOT work.
Older versions may not support the docker-compose 3.7 file format, which this project uses.
You can check the installed versions with:
```
docker --version
docker-compose --version
```


##### 2. Using the Command-Line Interface (CLI)
  - Download and setup the [TCP CLI](https://github.com/excellaco/xg#installation):
    this consists of a single binary which can be put in your PATH.
  - Copy the `xg.yaml` file from this repository to where you will run the CLI (you do not need to clone the entire repository)
  - Fill out the appropriate fields in `xg.yaml` with the desired values: (can quotes be used?)
    - projectName (directory)
    - ProjectName (for Gradle)
    - GroupName (for Gradle; typically "com.excella")
  - run `xg make`
    - Note: Your `xg` must be 0.2.1 (7/23/19) or later; `xg version` will show the installed version.
    - Note: if your private key for the tcp-java GitHub repo is not in `~/.ssh/id_rsa`, specify it with the '-i' or '--identity' flag:
      `xg make -i ~/.ssh/my_github_key` .  Failure to provide the correct private key will result in the error message `Error: Unable to clone repository remote`.
    - This will list a bunch of files and directories, prefixed with "F" for a file, "T" for a template, and "D" for a directory.
    - The result is in a subdirectory whose name is the same as the projectName you specified in `xg.yaml`.  Here we assume it's called my-project.
    - The result is *not* a Git workspace: there's no `.git` subdirectory.
  - Work around minor current `xg` limitations:
    - `cp -p xg.yaml my-project` # xg puts the version of xg.yaml as found in the repo under my-project

##### 3. Setup the application
This project uses OAuth2 and JWT for authentication / authorization.

[TODO: document the new generate-keystore script]

In order for the app to work correctly, you must create a Java KeyStore (.jks) file in the classpath (i.e., in src/main/resources).  There is a script to do this:

From the project root, run the following command:

`./generate-keystore`

It will ask you for a keystore filename (e.g. mytest.jks) and a keystore password.
It will create an .env file, then run `keytool`, which will ask you a bunch of questions.

Also, please ensure Flyway migrations are running. They include the DB setup for OAuth2 and Users. See the following for more information

 - https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html5/#appendix-schema
 - https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql


##### 4. Run the application

- Compile the app: `./start clean build`
  - Note: if you don't want the fancy font coloring and status bars, you can do `./start --console plain clean build`
  - Note: if the build fails on the format check, run this and then try again: `./start goJF`
  - Note: if you get a lot of FileNotFoundException test failures, check that your keystore (`.jks`) file is in the CLASSPATH and is readable

- Run the application: `./start bootrun`

- Navigate to:
    > http://localhost:8080/api/swagger-ui.html

When the app is not running:

- Run the tests: `./start testNG`

- Run the linter: `./start verGJF`

- Auto-fix linter warnings: `./start goJF`

- Run linter + tests + test report: `./test` (coverage report is generated under `build/jacocoHtml/index.html`)

##### 5  Use OAuth to interact with the API

You can use OAuth to interact with the app's API from the command line:

While the app is running (`./start bootrun`), from the project root directory:

`./get-token`

This will ask the API at `localhost:8080` to create a bearer token, then store that token in a file called `token`.  The token file can be used with CURL to access the rest of the API, for example:
`curl -K token localhost:8080/api/employee/2`


##### 6. Set up IntelliJ

- Install the Lombok Plugin

    1. Go to `File > Settings > Plugins`.
    2. Click on Browse repositories...
    3. Search for Lombok Plugin.
    4. Click on Install plugin.
    5. Restart IntelliJ IDEA.

- Import the project
    1. Select `Import Project`
    2. Select the directory where you cloned the repository
    3. Import as a gradle project and select a JDK >= 11
    4. Select to use the gradle wrapper configuration from the project



##### 7. Tech Challenge Architecture

The Java repository represents one of the possible backends that can be used.

![Tech Challenge Architecture Diagram](architechture.png)

To connect a front-end application to the API, please see either the Angular or React tech challenge repositories.

  -  [Angular Repository](https://github.com/excellaco/tcp-angular)
  - [React Repository](https://github.com/excellaco/tcp-react)

##### 8.  Reactive Web Frameworks

[Springboot Webflux](https://spring.io/guides/gs/reactive-rest-service/) is a reactive web framework.  For more information on reactive design and its basic principles, we suggest looking at the [Reactive Manifesto](https://www.reactivemanifesto.org/).

For a more detailed guide to understanding how to handle reactive and functional types like `Mono` and `Flux`, please refer to our [Java React Tutorial](https://github.com/excellalabs/reactive-in-java)

##### 9. Deployment to the ECS Cluster

The Jenkinsfile deals with packaging the tcp-java-api application and deploying it to the ECS Cluster.

