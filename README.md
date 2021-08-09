# hex-thai-star

[![CircleCI](https://circleci.com/gh/hex-arch-training/hex-thai-star/tree/main.svg?style=shield)](https://circleci.com/gh/hex-arch-training/hex-thai-star/tree/main)
![Java CI with Maven](https://github.com/hex-arch-training/hex-thai-star/actions/workflows/maven.yml/badge.svg)
[![CodeQL](https://github.com/hex-arch-training/hex-thai-star/workflows/CodeQL/badge.svg)](https://github.com/hex-arch-training/hex-thai-star/actions?query=workflow%3ACodeQL "Code quality workflow status")
![License](https://img.shields.io/badge/License-MIT-blue)

## Note on Booking out adapters

The Booking subdomain has two DB-Related out adapters implemented:

* JPA adapter (see `booking/booking-adapter-out-jpa`),
* JDBI adapter (see `booking/booking-adapter-out-jdbi`).

Both adapters are mapping onto the same database structure.
By default, the `springboot` app (`configuration/springboot`) configures JPA adapter.
This can be changed by using `jdbi` Spring profile - then the `jdbi` implementation will be used.
It is noteworthy, that `jdbi` implementation still uses `auto ddl` code from `jpa` - it is okay for time being as we do not have Liquibase/Flyway solution in place (yet).
