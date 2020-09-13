
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/ldapSpring/src
COPY pom.xml /home/ldapSpring
RUN mvn -f /home/ldapSpring/pom.xml clean package

FROM openjdk:11-jre-slim

COPY --from=build /home/ldapSpring/target/*.jar /usr/local/lib/api.jar

ENTRYPOINT ["java","-jar","/usr/local/lib/api.jar"]

EXPOSE 8080