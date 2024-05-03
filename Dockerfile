FROM openjdk:8
ADD target/person-manager-docker.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
