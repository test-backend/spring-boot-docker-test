# SpringBoot - Docker test

## Before anything...
Check if you have installed these features in your machine:

* **JDK 8**,
* **Maven 3.6.x**,
* **Docker client** and **Docker desktop**

## Build 
You can build this project using

```mvn clean install -U```

## Run it!
First, start the docker container with the DB:

```docker-compose up```

then, you can run the Spring Boot application via:

```mvn spring-boot:run```
