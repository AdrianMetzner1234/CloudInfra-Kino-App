#/bin/bash

REGISTRY_RESERVIERUNG="adrianmetzner/ci-kino-reservierungs-service"
REGISTRY_WEBUI="adrianmetzner/ci-kino-ui"
REGISTRY_PROGRAM="adrianmetzner/ci-kino-film-programm-service"
VERSION="1.0.0"


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

