# hex-thai-star

[![CircleCI](https://circleci.com/gh/hex-arch-training/hex-thai-star/tree/main.svg?style=shield)](https://circleci.com/gh/hex-arch-training/hex-thai-star/tree/main)
![Java CI with Maven](https://github.com/hex-arch-training/hex-thai-star/actions/workflows/maven.yml/badge.svg)
[![CodeQL](https://github.com/hex-arch-training/hex-thai-star/workflows/CodeQL/badge.svg)](https://github.com/hex-arch-training/hex-thai-star/actions?query=workflow%3ACodeQL "Code quality workflow status")
[![Code Climate](https://codeclimate.com/github/hex-arch-training/hex-thai-star.svg?branch=main)](https://codeclimate.com/github/hex-arch-training/hex-thai-star?branch=main)
[![Issue Count](https://codeclimate.com/github/hex-arch-training/hex-thai-star/badges/issue_count.svg)](https://codeclimate.com/github/hex-arch-training/hex-thai-star)
![License](https://img.shields.io/badge/License-MIT-blue)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fhex-arch-training%2Fhex-thai-star.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fhex-arch-training%2Fhex-thai-star?ref=badge_shield)

## Note on Booking ***out*** adapters

The Booking subdomain has the following DB-Related ***out*** adapters implemented:

* JPA adapter (see `booking/booking-adapter-out-jpa`),
* JDBI adapter (see `booking/booking-adapter-out-jdbi`),
* [DynamoDB](https://aws.amazon.com/dynamodb) adapter (see [`booking/booking-adapter-out-dynamodb`](https://github.com/hex-arch-training/hex-thai-star/tree/main/booking/booking-adapter-out-dynamodb)).

The first two above-mentioned adapters can be used to access a relational database (e.g. MySQL, PostgreSQL, etc.) and are mapping onto the same database structure. The latter adapter is tight to a specific database product - [DynamoDB](https://aws.amazon.com/dynamodb) which is a NoSQL database.

## Note on Booking ***in*** adapters

The Booking subdomain has the following REST-related ***in*** adapters implemented:

* [Spring Web MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html) (see [`booking/booking-adapter-in-springweb`](https://github.com/hex-arch-training/hex-thai-star/tree/main/booking/booking-adapter-in-springweb)),
* [JAX-RS](https://github.com/eclipse-ee4j/jaxrs-api) API with [RESTEasy](https://resteasy.github.io/) implementation provided by [Quarkus](https://quarkus.io/) (see [`booking/booking-adapter-in-quarkusweb`](https://github.com/hex-arch-training/hex-thai-star/tree/main/booking/booking-adapter-in-quarkusweb)),
* [AWS Lambda](https://aws.amazon.com/lambda/) adapter (see [`booking/booking-adapter-out-awslambda`](https://github.com/hex-arch-training/hex-thai-star/tree/main/booking/booking-adapter-out-awslambda)).

## Note on how Booking can be run, that is, available configurations

The Booking (micro)service / app can be started using the following configurations:

* [`Spring Boot`](https://spring.io/projects/spring-boot) application (see [`configuration/springboot`](https://github.com/hex-arch-training/hex-thai-star/tree/main/configuration/springboot)) combines the following adapters:
    * [Spring Web MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
    * JPA adapter. This can be changed by using `jdbi` Spring profile - then the `jdbi` implementation will be used.
      It is noteworthy, that `jdbi` implementation still uses `auto ddl` code from `jpa` - it is okay for time being as we do not have Liquibase/Flyway solution in place (yet).
* [`Quarkus`](https://quarkus.io/) application (see [`configuration/quarkus`](https://github.com/hex-arch-training/hex-thai-star/tree/main/configuration/quarkus)) combines the following adapters:
    * [JAX-RS](https://github.com/eclipse-ee4j/jaxrs-api)
    * JPA adapter
* [`AWS Lambda`](https://aws.amazon.com/lambda/) application (see [`configuration/awslambda`](https://github.com/hex-arch-training/hex-thai-star/tree/main/configuration/awslambda)) combines the following adapters:
    * [AWS Lambda](https://aws.amazon.com/lambda)
    * [DynamoDB](https://aws.amazon.com/dynamodb)
  
  The automatic deployment to AWS is implemented using the [Serverless Framework](https://serverless.com/) and the [CircleCI](https://app.circleci.com/pipelines/github/hex-arch-training/hex-thai-star?branch=main) platform. The Booking service (its Table endpoint) is available [here](https://f6pyei2me3.execute-api.eu-central-1.amazonaws.com/booking/tables). Be patient: the ***cold*** start takes about 6 seconds while the ***hot*** one about 0.3 seconds.

* [`Quarkus`](https://quarkus.io/) on [`AWS Lambda`](https://aws.amazon.com/lambda/) application (see [`configuration/quarkus-awslambda`](https://github.com/hex-arch-training/hex-thai-star/tree/main/configuration/quarkus-awslambda)) combines the following adapters:
    * [JAX-RS](https://github.com/eclipse-ee4j/jaxrs-api)
    * [DynamoDB](https://aws.amazon.com/dynamodb)
   
  This is in fact a [Quarkus](https://quarkus.io/) application which uses the [`quarkus-amazon-lambda-http`](https://quarkus.io/guides/amazon-lambda-http) and [`quarkus-amazon-dynamodb`](https://quarkus.io/guides/amazon-dynamodb) extensions. During the build on the [CircleCI](https://app.circleci.com/pipelines/github/hex-arch-training/hex-thai-star?branch=main) platform, the [`Quarkus`](https://quarkus.io/) app is built with with [GraalVM](https://www.graalvm.org/)
  and the native executable is then deployed using the [Serverless Framework](https://serverless.com/). The Booking service (its Table endpoint) is available [here](https://zhjq4x9nq8.execute-api.eu-central-1.amazonaws.com/booking/tables). Now the ***cold*** start takes about 1 second while the ***hot*** one is more or less the same.


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fhex-arch-training%2Fhex-thai-star.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fhex-arch-training%2Fhex-thai-star?ref=badge_large)