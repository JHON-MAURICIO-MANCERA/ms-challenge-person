FROM openjdk:17-jdk-slim
EXPOSE 8080
RUN mkdir /app
RUN mkdir /config
#VOLUME /tmp

COPY applications/build/libs/ms-challenge-person.jar app/app.jar
COPY applications/app-service/build/resources/main/application.yaml config/application.yaml

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]