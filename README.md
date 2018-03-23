[![Build Status](https://travis-ci.org/petarmitrovic/hobnob.svg?branch=develop)](https://travis-ci.org/petarmitrovic/hobnob)

# Hobnob - Simple blogging platform

A sample Spring Boot application used as a playground.

## Prerequisites

### Dokerized development

TBD

### Non-dockerized development

* JDK 1.8
* Maven 3.+

## Building the app

```
mvn clean package
```

## Configuration

The main application configuration can be found in `application/src/resources/application.yml`, 
and can be overriden by providing an external config poperties or config file (https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files).

## Application Endpoints

Applicatioin context root http://localhost:8080

Swagger docs: http://localhost:8080/swagger-ui.html
