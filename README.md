# printscript
Parser project for Ing-Sis at Universidad Austral.

## Prerequisites
- JDK 1.11

## Usage
Running ` build gradle ` builds the projects, runs formatter and linter, and runs tests generating a code-coverage report.

## Formatter
The formatting tool used is [Beekeeper Formatter](https://github.com/beekpr/beekeeper-gradle-plugins/tree/master/beekeeper-formatter-plugin). This plugin adds all the functionality of Spotless, allowing to run tasks such as `spotlessCheck` and `spotlessApply`

## Linter
The linter used is [Checkstyle](https://checkstyle.sourceforge.io/). It is run when building the project.

## Code-coverage tool
The coded-coverage tool used is [JaCoCo](https://www.eclemma.org/jacoco/). After running `gradle check` the reports are generated at `build/reports/jacoco`.

## Workflows
Two workflows were used:
### Gradle Build
Runs `gradle build` together with tests whenever new commits are pushed to `develop`.
### Gradle Package
Publishes project modules to Github Packages whenever a there is a new release on `main`.
