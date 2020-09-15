FROM maven:3.6.3-jdk-8 AS builder

# clients bauen
COPY MicroFilmprogrammServiceClient MicroFilmprogrammServiceClient
RUN mvn -f MicroFilmprogrammServiceClient/pom.xml install
COPY MicroReservierungsServiceClient MicroReservierungsServiceClient
RUN mvn -f MicroReservierungsServiceClient/pom.xml install

# eigene Dependencies laden
COPY MicroFilmprogrammService/pom.xml MicroFilmprogrammService/
RUN mvn -f MicroFilmprogrammService/pom.xml dependency:go-offline

# bauen
COPY MicroFilmprogrammService/src MicroFilmprogrammService/src
RUN mvn -f MicroFilmprogrammService/pom.xml package


# eigentliches Image
FROM openjdk:8-jre

WORKDIR /app
COPY --from=builder /MicroFilmprogrammService/target/*.jar ./

EXPOSE 50000
ENTRYPOINT ["java","-jar","MicroFilmprogrammService-0.0.1-SNAPSHOT.jar", "50000"]
