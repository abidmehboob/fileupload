FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY /var/lib/jenkins/workspace/fileupload/target/fileupload-0.0.1-SNAPSHOT.jar /var/lib/jenkins/workspace/fileupload/target/app.jar
ENTRYPOINT ["java","-jar","/var/lib/jenkins/workspace/fileupload/target/app.jar"]
