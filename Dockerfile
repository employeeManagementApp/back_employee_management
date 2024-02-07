FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ..
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080