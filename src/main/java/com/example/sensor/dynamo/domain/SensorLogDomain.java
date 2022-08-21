package com.example.sensor.dynamo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorLogDomain implements Serializable {

  private UUID id;
  private LocalDateTime when;
  private Float temperature;

  public SensorLogDomain(UUID id, LocalDateTime when, Float temperature) {
    this.id = id;
    this.when = when;
    this.temperature = temperature;
  }

  public SensorLogDomain() {
  }

  public SensorLogDomain(LocalDateTime when, Float temperature) {
  }
}
