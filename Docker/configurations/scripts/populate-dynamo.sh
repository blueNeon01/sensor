#!/bin/bash

PATH_DYNAMODB_CONFIGURATION="populate"

awslocal --endpoint-url="http://localhost:4566" dynamodb put-item --table-name tb_sensor_log --item "file://populate/populate-sensor.json"

awslocal --endpoint-url="http://localhost:4566" dynamodb put-item --table-name tb_sensor_log_aggregated --item "file://populate/populate-sensor-aggregated.json"
#awslocal --endpoint-url="http://localhost:4566" dynamodb put-item --table-name tb_sensor_log_aggregated --item "file://populate/populate-sensor-aggregated02.json"
#awslocal --endpoint-url="http://localhost:4566" dynamodb put-item --table-name tb_sensor_log_aggregated --item "file://populate/populate-sensor-aggregated03.json"
#awslocal --endpoint-url="http://localhost:4566" dynamodb put-item --table-name tb_sensor_log_aggregated --item "file://populate/populate-sensor-aggregated04.json"

echo -e "\n listing dynamodb"

