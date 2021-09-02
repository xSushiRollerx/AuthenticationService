#
# Package stage
#
FROM openjdk:11-jre-slim
COPY target/authentication-service-1.0.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]
