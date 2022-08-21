package com.example.sensor.dynamo.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import java.time.LocalDateTime;
import java.util.Objects;

public class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

  @Override
  public String convert(final LocalDateTime object) {
    return Objects.nonNull(object) ? object.toString() : null;
  }


  @Override
  public LocalDateTime unconvert(final String object) {
    return Objects.nonNull(object) ? LocalDateTime.parse(object) : null;
  }
}
