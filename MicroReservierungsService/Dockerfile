FROM maven:3.6.3-jdk-11

# clients bauen
COPY MicroFilmprogrammServiceClient MicroFilmprogrammServiceClient
RUN mvn -f MicroFilmprogrammServiceClient/pom.xml install
COPY MicroReservierungsServiceClient MicroReservierungsServiceClient
RUN mvn -f MicroReservierungsServiceClient/pom.xml install

# eigene Dependencies laden
COPY MicroReservierungsService/pom.xml MicroReservierungsService/
RUN mvn -f MicroReservierungsService/pom.xml dependency:go-offline  -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

# bauen
COPY MicroReservierungsService/src MicroReservierungsService/src
RUN mvn -f MicroReservierungsService/pom.xml package  -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

# Start konfigurieren
EXPOSE 45000
WORKDIR MicroReservierungsService/target/
ENTRYPOINT ["java","-jar","MicroReservierungsService-0.0.8-SNAPSHOT.jar", "45000"]
