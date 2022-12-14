package com.example.sensor.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.example.sensor.dynamo.converters.LocalDateTimeConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "tb_sensor_log_aggregated")
public class SensorLogAggregatedModel implements Serializable {

  private static final long serialVersionUID = -9103626054274578757L;
  public static final String INDEX_BY_DATE = "x001";

  @DynamoDBAutoGeneratedKey
  @DynamoDBHashKey(attributeName = "id_sensor")
  private UUID id;

  @DynamoDBIndexRangeKey(attributeName = "when_log", globalSecondaryIndexName = INDEX_BY_DATE)
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
