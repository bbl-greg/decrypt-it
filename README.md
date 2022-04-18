# Library

> written by Greg Drake

This application is designed to provide a basic REST endpoint and connected service
that keeps a running average and calculated standard deviation for all numbers submitted to it.

The technology behind it:

* Java 17
* Dropwizard
* Running average and std deviation values come from shared singleton resource inject via Guice

Application structure and pattern loosely follows Ports and Adapter pattern because of its high level of decoupling
between components. This decoupling allows future re-work and updates without having to rewrite or impact the
application's core business logic (calculating std dev and average/AES symmetric encryption and decryption).

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
<br>

## Ways to Improve

Currently this application is simply a proof of concept. To prepare it for production, one would need to consider
switching from the local store sharing state between api calls and instead use a more robust stateful streaming pattern.

Kafka and Flink would be ideal open-source candidates for taking inbound request messages and applying near-realtime,
low-latency stateful transformations. The API service could use async handlers to publish and subscribe to the Kafka
pipeline - allowing it to wait for calculations to be performed in a non-blocking manner.

Also, a vault service to store the AES key between server sessions would be ideal over the session-dependant resource I
used. Hashicorp comes to mind as a possible secure store, but likely in-house proprietary solutions exist. Additionally,
using CTR encryption instead of CBC may be better for this use case, encryptions and decryptions could be parallelized
allowing for shorter wait times while the API runs its cryptography service. Short wait times in a customer-facing API
service are always welcome.