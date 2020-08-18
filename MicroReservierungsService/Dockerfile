FROM maven:3.6.3-jdk-8

# clients bauen
COPY MicroFilmprogrammServiceClient MicroFilmprogrammServiceClient
RUN mvn -f MicroFilmprogrammServiceClient/pom.xml install
COPY MicroReservierungsServiceClient MicroReservierungsServiceClient
RUN mvn -f MicroReservierungsServiceClient/pom.xml install

# eigene Dependencies laden
COPY MicroReservierungsService/pom.xml MicroReservierungsService/
RUN mvn -f MicroReservierungsService/pom.xml dependency:go-offline

# bauen
COPY MicroReservierungsService/src MicroReservierungsService/src
RUN mvn -f MicroReservierungsService/pom.xml package

# Start konfigurieren
EXPOSE 45000
WORKDIR MicroReservierungsService/target/
ENTRYPOINT ["java","-jar","MicroReservierungsService-0.0.8-SNAPSHOT-onejar.jar", "45000"]
