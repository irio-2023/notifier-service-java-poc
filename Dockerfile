FROM openjdk:17
EXPOSE 8080
WORKDIR /app
COPY target/notifier-service.jar /app/notifier-service.jar
ENTRYPOINT ["java", "-jar", "notifier-service.jar"]
