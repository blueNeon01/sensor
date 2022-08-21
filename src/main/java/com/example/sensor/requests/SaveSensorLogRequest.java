package com.example.sensor.requests;

import com.example.sensor.dynamo.domain.SensorLogDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class SaveSensorLogRequest {
  @JsonProperty("temperature")
  private Float temperature;

  @JsonProperty("when")
  private LocalDateTime when;

  public SaveSensorLogRequest() {

  }

  public SaveSensorLogRequest(Float temperature, LocalDateTime when) {
    this.temperature = temperature;
    this.when = when;
  }


  public SensorLogDomain toSensorLog() {
    return new SensorLogDomain(this.when, this.temperature);
  }

  public Float getTemperature() {
    return temperature;
  }

  public void setTemperature(Float temperature) {
    this.temperature = temperature;
  }

  public LocalDateTime getWhen() {
    return when;
  }

  public void setWhen(LocalDateTime when) {
    this.when = when;
  }

}