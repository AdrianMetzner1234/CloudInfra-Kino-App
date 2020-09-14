FROM java:8-jre

WORKDIR /root
ADD target/*.jar ./

EXPOSE 50000
ENTRYPOINT ["java","-jar","MicroFilmprogrammService-0.0.6-SNAPSHOT-onejar.jar", "50000"]
