FROM openjdk:17-jdk-slim

USER root

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8000

CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "./app.jar"]
