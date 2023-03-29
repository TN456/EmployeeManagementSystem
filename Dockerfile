FROM openjdk:17
LABEL maintainer="Tanuja"
ADD target/EmployeeManagementSystem-0.0.1-SNAPSHOT.jar employee-docker.jar
ENTRYPOINT [ "java","-jar","employee-docker.jar" ]