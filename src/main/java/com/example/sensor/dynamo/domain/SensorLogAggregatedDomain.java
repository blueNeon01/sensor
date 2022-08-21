package com.example.sensor.dynamo.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorLogAggregatedDomain {

  private UUID id;
  private LocalDateTime when;
  private Float averageTemperature;
  private Integer totalRecords;

  public SensorLogAggregatedDomain(UUID id, LocalDateTime when, Float averageTemperature,
      Integer totalRecords) {
    this.id = id;
    this.when = when;
    this.averageTemperature = averageTemperature;
    this.totalRecords = totalRecords;
  }

  public SensorLogAggregatedDomain() {
  }

}