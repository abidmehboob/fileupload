FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/fileupload-0.0.1-SNAPSHOT.jar app.jar

