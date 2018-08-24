FROM openjdk:8-jre-alpine
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000","-jar","/app.jar"]
EXPOSE 8080
EXPOSE 8000