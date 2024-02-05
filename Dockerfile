FROM maven:3.8.4 AS builder


COPY pom.xml /app/
WORKDIR /app


RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn package -DskipTests


FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080
