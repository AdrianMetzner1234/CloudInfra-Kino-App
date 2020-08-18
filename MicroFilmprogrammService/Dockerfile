FROM java:8-jre

WORKDIR /root
ADD target/*.jar ./

EXPOSE 50000
ENTRYPOINT ["java","-jar","MicroFilmprogrammService-0.0.8-SNAPSHOT-onejar.jar", "50000"]
