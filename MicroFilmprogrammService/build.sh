#/bin/bash

NAME="film-program-service"
REGISTRY="504342896137.dkr.ecr.eu-central-1.amazonaws.com/tickets"
VERSION="0.0.7"

mvn package
docker build -t $NAME:$VERSION -t $REGISTRY:$VERSION ./

docker push $REGISTRY:$VERSION
