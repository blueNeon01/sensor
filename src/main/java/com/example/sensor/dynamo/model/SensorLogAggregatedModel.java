package com.example.sensor.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.example.sensor.dynamo.converters.LocalDateTimeConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "tb_sensor_log_aggregated")
public class SensorLogAggregatedModel implements Serializable {

  private static final long serialVersionUID = -9103626054274578757L;

  @DynamoDBHashKey (attributeName = "aggregated_type" )
  private String aggregationType;

  @DynamoDBRangeKey(attributeName = "when_log")
  @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
  private LocalDateTime when;

  @DynamoDBAttribute(attributeName = "averageTemperature")
  private Float averageTemperature;

  @DynamoDBAttribute(attributeName = "totalRecords")
  private Integer totalRecords;

  public SensorLogAggregatedModel(LocalDateTime when, Float averageTemperature,
      Integer totalRecords) {
  }

  public SensorLogAggregatedModel() {
  }
}
