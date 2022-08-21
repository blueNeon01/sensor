#!/bin/bash

PATH_DYNAMODB_CONFIGURATION="configurations/dynamodb"

awslocal --endpoint-url=$URL_LOCALSTACK dynamodb create-table --cli-input-json "file://dynamodb/dynamodb-sensor-table.json"

awslocal --endpoint-url=$URL_LOCALSTACK dynamodb create-table --cli-input-json "file://dynamodb/dynamodb-sensor-aggregated.json"

echo -e "\n listing dynamodb"
