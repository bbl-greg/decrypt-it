# Library

> written by Greg Drake

This application is designed to provide a basic REST endpoint and connected service
that keeps a running average and calculated standard deviation for all numbers submitted to it.

The technology behind it:

* Java 17
* Postgres
* Dropwizard

## Installing / Getting started

#### Using `docker-compose`

In the terminal run the following command:

```console
$ docker-compose up
```

#### Using Gradle (with H2 or local Postgres database)

First compile an application:

```console
$ gradle shadowJar
```

Then to run it:

```console
$ java -jar build/libs/decrypt-it-1.0-SNAPSHOT-all.jar serve decrypt-it.yml
```

For a second option, check in the configuration file - `src/main/resources/application.yml` for profile *local-postgres*
if connection details are correct and if so, run the command:

```console
$ mvn spring-boot:run -P local-postgres
```

#### Inside IntelliJ (with H2 or Postgres database)

First configure how you run the `LibraryHexagonalApplication.java` by adding `--spring.profiles.active=h2` (for H2
database) or `--spring.profiles.active=postgres` (for Postgres database) as a **Program argument**.

Then just run the `LibraryHexagonalApplication.java` class so it will use H2 database (you don't need to have postgres
database up and running).
