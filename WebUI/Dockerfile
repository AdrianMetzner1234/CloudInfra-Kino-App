FROM maven:3.6.3-jdk-8 AS builder

# clients bauen
COPY MicroFilmprogrammServiceClient MicroFilmprogrammServiceClient
RUN mvn -f MicroFilmprogrammServiceClient/pom.xml install
COPY MicroReservierungsServiceClient MicroReservierungsServiceClient
RUN mvn -f MicroReservierungsServiceClient/pom.xml install

# eigene Dependencies laden
COPY WebUI/pom.xml WebUI/
RUN mvn -f WebUI/pom.xml dependency:go-offline

# bauen
COPY WebUI/src WebUI/src
RUN mvn -f WebUI/pom.xml package


# eigentliches Image
FROM openjdk:8-jre

WORKDIR /app
COPY --from=builder /WebUI/target/*.jar ./

EXPOSE 8081
ENTRYPOINT ["java","-jar","WebUI-0.0.1-SNAPSHOT.jar"]
