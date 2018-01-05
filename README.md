spring-cloud-netflix-eureka-ribbon
==================================

A simple POC to check how eureka & ribbon work together.

Steps
=====

- Load the module that you want to try (eureka, ribbon, ribbon-eureka) into your ide.

- Build it using `mvn clean install`.

- Run it using `mvn spring-boot:run`. In case you need to specify a custom port `mvn spring-boot:run -Dserver.port=9092`.

Hosting on pivotal cf
=====================

[Link](eureka/README.md)