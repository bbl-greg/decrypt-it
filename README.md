# Library

> written by Greg Drake

This application is designed to provide a basic REST endpoint and connected service
that keeps a running average and calculated standard deviation for all numbers submitted to it.

The technology behind it:

* Java 17
* Dropwizard
* Running average and std deviation values come from shared singleton resource inject via Guice

## Installing / Getting started

#### Using Gradle

First compile an application:

```console
$ gradle shadowJar
```

Then to run it:

```console
$ java -jar build/libs/decrypt-it-1.0-SNAPSHOT-all.jar serve decrypt-it.yml
```

## Running Unit Tests

#### Using Gradle

Run unit tests:

```console
$ gradle clean test
```

# REST API

Using Decrypt-It REST APIs:

## Push and Recalculate

### Request

`POST /runningvalue/push-and-recalculate`

    curl --request PUT \
    --url http://localhost:8080/runningvalue/push-and-recalculate \
    --header 'Content-Type: application/json' \
    --data '{"value" : "some_number"}'

### Response

    { "average": "some_value_calculated", "stdDeviation": "some_value_calculated" }

## Push, Recalculate, And Encrypt

### Request

`POST /runningvalue/push-and-recalculate-and-encrypt`

    curl --request PUT \
    --url http://localhost:8080/runningvalue/push-and-recalculate-and-encrypt \
    --header 'Content-Type: application/json' \
    --data '{"value" : "some_number"}'

### Response

    { "average": "some_value_encrypted", "stdDeviation": "some_value_encrypted" }

## Decrypt

### Request

`POST /runningvalue/runningvalue/decrypt`

    curl --request PUT \
    --url http://localhost:8080/runningvalue/decrypt \
    --header 'Content-Type: application/json' \
    --data '{"value" : "/9+E6BQM1Nlx8NWSb1E7dQ=="}'

### Response

    { "decryptedValue": "1.5" }

  <br>

#### Decrypt Note

<sub>Only values encrypted during the current server session can be decrypted. The symmetric key is unique to each
server cycle. Trying to decrypt a key generated during a previous server session will result in a 500 error.