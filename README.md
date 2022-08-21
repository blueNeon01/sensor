### About The Project
This is an assignment project

### Project conception
The project was a assignment to create two endpoints. 
The first endpoint is to save temperature log and second to get that information aggregated.

### Stack
1 - java 11 \
2 - localstack AWS \
3 - dynamodb

### To run the application
1 - Download the application content \
2 - Start Docker \
3 - Run script setup-local.sh to start the container with localstack to create tables and populate itens \

```shell
./setup-local.sh
```
4 - Start application

### Save sensor log

1 - Curl request POST /log

```shell
curl --location --request POST 'http://localhost:8080/log' \
--header 'Content-Type: application/json' \
--data-raw '[
{
"temperature": 35,
"when": "2022-08-21T04:44:23"
},
{
"temperature": 33,
"when": "2022-08-21T04:44:23"
},
{
"temperature": 34,
"when": "2022-08-21T04:44:23"
}
]
```
### Commands awsLocal

```shell
awslocal --endpoint-url=http://localhost:4566 dynamodb list-tables
awslocal --endpoint-url=http://localhost:4566 dynamodb scan --table-name tb_sensor_log
````
