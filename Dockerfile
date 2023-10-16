FROM openjdk:17-jdk-slim
MAINTAINER vasilie.alexandra
WORKDIR /app
ADD target/*.jar bookstore-backend.jar
ENTRYPOINT ["java","-jar","/bookstore-backend.jar"]