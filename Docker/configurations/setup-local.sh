#!/usr/bin/bash

PATH_SCRIPTS="scripts"

#start local stack
echo -e "\nSetting environment variables"

export URL_LOCALSTACK="http://localhost:4566"
export AWS_SECRET_KEY=fakeSecretKey
export AWS_SECRET_KEY_ID=fakeSecretKeyId
export AWS_SECRET_ACCESS_KEY=fakeSecretAccessKey
export AWS_DEFAULT_REGION=us-west-1
export HTTP_PROXY=
export HTTPS_PROXY=

echo "\nDone"

docker-compose --project-name sensor up -d

echo -e "\nCreating dynamodb"
sh "$PATH_SCRIPTS/make-dynamo.sh"

echo -e "\nPopulation dynamodb"
sh "$PATH_SCRIPTS/populate-dynamo.sh"



echo -e "\nEnvironment setup done"
