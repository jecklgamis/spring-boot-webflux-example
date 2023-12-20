## Spring Boot WebFlux Example

[![Build](https://github.com/jecklgamis/spring-boot-webflux-example/actions/workflows/build.yml/badge.svg)](https://github.com/jecklgamis/spring-boot-webflux-example/actions/workflows/build.yml)

This is an example [Spring Boot WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html) 
app using Java. Spring WebFlux uses [Reactor](https://projectreactor.io/) library, an implementation of Reactive Streams 
Specs for building non-blocking applications.

This example:
* Uses [Reactor Netty](https://github.com/reactor/reactor-netty), the default Web container for Spring WebFlux
* Uses functional endpoints 
* Contains GET/POST/DELETE handler examples (see `KeyValueStoreHandler.java`)
* Contains Multipart file upload handler example  (see `FileUploadHandler.java`)

## Running The App
Ensure you have Java 21 or later.
```
./mvnw clean package
java -jar target/spring-boot-webflux-example.jar
```

## Running The App Using Docker
Ensure you have a working Docker environment.
```
make dist image run
```

## Testing The Endpoints
Point your browser to `http://localhost:8080` or use `curl` in command line.

```
curl -v  http://localhost:8080/
```
Actuator endpoints:
* `http://localhost:8080/actuator/metrics`
* `http://localhost:8080/actuator/health`


