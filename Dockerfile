FROM openjdk:17-alpine
MAINTAINER vasilie.alexandra
COPY target/demo-0.0.1-SNAPSHOT.jar bookstore-backend.jar
ENTRYPOINT ["java","-jar","/bookstore-backend.jar"]