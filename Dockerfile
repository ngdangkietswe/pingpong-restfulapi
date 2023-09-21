FROM openjdk:17-alpine

WORKDIR /app

COPY /target/pingpong-restfulapi-0.0.1-SNAPSHOT.jar pingpong-restfulapi.jar

ENTRYPOINT ["java", "-jar", "pingpong-restfulapi.jar"]