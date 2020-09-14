#/bin/bash

NAME="web-ui"
REGISTRY="504342896137.dkr.ecr.eu-central-1.amazonaws.com/ui"
VERSION="0.0.9"

mvn package
docker build -t $NAME:$VERSION -t $REGISTRY:$VERSION ./

docker push $REGISTRY:$VERSION
