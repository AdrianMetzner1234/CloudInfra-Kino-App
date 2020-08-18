#/bin/bash

REGISTRY_RESERVIERUNG="504342896137.dkr.ecr.eu-central-1.amazonaws.com/reservierung"
REGISTRY_WEBUI="504342896137.dkr.ecr.eu-central-1.amazonaws.com/ui"
REGISTRY_PROGRAM="504342896137.dkr.ecr.eu-central-1.amazonaws.com/program-service"
VERSION="0.0.10"


# build program
cd MicroFilmprogrammService
mvn package
cd ../
docker build -t program:$VERSION -t $REGISTRY_PROGRAM:$VERSION ./MicroFilmprogrammService
docker push $REGISTRY_PROGRAM:$VERSION

# build Reservierung
docker build -t reservierung:$VERSION -t $REGISTRY_RESERVIERUNG:$VERSION -f ./MicroReservierungsService/Dockerfile .
docker push $REGISTRY_RESERVIERUNG:$VERSION

#webui
docker build -t kino-ui:$VERSION -t $REGISTRY_WEBUI:$VERSION -f ./WebUI/Dockerfile .
docker push $REGISTRY_WEBUI:$VERSION

