FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080
