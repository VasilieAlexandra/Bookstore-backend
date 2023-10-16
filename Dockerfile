FROM openjdk:17-jdk-slim
MAINTAINER vasilie.alexandra
ADD target/demo-0.0.1-SNAPSHOT.jar bookstore-backend.jar
ENTRYPOINT ["java","-jar","/bookstore-backend.jar"]