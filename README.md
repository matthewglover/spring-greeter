# Spring Boot Greeter Example Application

## What

A simple toy Spring Boot application, to demonstrate setting up CI and CD deployment pipelines.

Exposes a single route `/greet`, which responds with `Hello, World!`

## How

### Overview

A Kotlin Reactive Spring Boot application.

Build uses Gradle with the Kotlin DSL.

### Docker

The app can be built to a Docker container image, which is pushed to Docker Hub.

This uses the Gradle Jib plugin. The current configuration authorises with a username and password. For security reasons
the password has not been submitted to source control. Instead it's included locally in a `gradle.properties` file 
(which is excluded from source control) and referenced in the `build.gradle.kts`.

In order to replicate this in Circle CI, the following environment variable needs to be set in Circle CI:
`ORG_GRADLE_PROJECT_dockerHubPassword=<docker-hub-password>`.

## Quickstart

To run the tests: `./gradlew check`

To build the app: `./gradlew bootJar`

To run and build the app: `./gradlew bootRun`

To build the image and send to a container registry: `./gradlew jib` (after configuring the jib section in 
`build.gradle.kts`)
