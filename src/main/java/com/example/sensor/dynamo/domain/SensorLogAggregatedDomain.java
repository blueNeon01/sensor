package com.example.sensor.dynamo.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorLogAggregatedDomain {

//  private UUID id;
  private LocalDateTime when;
  private Float averageTemperature;
  private Integer totalRecords;

  private String aggregationType;

  public SensorLogAggregatedDomain(LocalDateTime when, Float averageTemperature,
      Integer totalRecords, String aggregationType) {
    this.when = when;
    this.averageTemperature = averageTemperature;
    this.totalRecords = totalRecords;
    this.aggregationType = aggregationType;
  }

  public SensorLogAggregatedDomain() {
  }

}