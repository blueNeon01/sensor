package com.example.sensor.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.example.sensor.dynamo.converters.LocalDateTimeConverter;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@DynamoDBTable(tableName = "tb_sensor_log")
public class SensorLogModel {

  @DynamoDBAutoGeneratedKey
  @DynamoDBHashKey(attributeName = "id_sensor")
  private UUID id;

  @DynamoDBRangeKey(attributeName = "when_log")
  @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
  private LocalDateTime when;

  @DynamoDBAttribute(attributeName = "temperature")
  private Float temperature;

  public SensorLogModel(LocalDateTime when, Float temperature) {
    this.when = when;
    this.temperature = temperature;
  }

//  public SensorLogModel(UUID id, LocalDateTime when, Float temperature) {
//    this.id = id;
//    this.when = when;
//    this.temperature = temperature;
//  }

  public SensorLogModel() {
  }
}

