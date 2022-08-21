package com.example.sensor.requests;

import com.example.sensor.enums.AggregationType;
import com.example.sensor.requests.exceptions.AggregationTypeNotAllowedException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Locale;
@JsonInclude(Include.NON_NULL)
public class ListSensorLogsRequest {
  @JsonProperty("aggregationType")
  private AggregationType aggregationType;
  @JsonProperty("from")
  private LocalDateTime from;
  @JsonProperty("to")
  private LocalDateTime to;

  public ListSensorLogsRequest() {
  }

  public ListSensorLogsRequest(String aggregationType, LocalDateTime from, LocalDateTime to)
      throws AggregationTypeNotAllowedException {

    if (aggregationType == null) {
      aggregationType = AggregationType.DAILY.toString();
    }

    this.setAggregationType(aggregationType);
    this.from = from;
    this.to = to;
  }

  public AggregationType getAggregationType() {
    if (this.aggregationType == null) {
      this.aggregationType = AggregationType.DAILY;
    }
    return aggregationType;
  }

  public void setAggregationType(AggregationType aggregationType)
      throws AggregationTypeNotAllowedException {
    this.aggregationType = aggregationType;
  }

  public void setAggregationType(String aggregationType) throws AggregationTypeNotAllowedException {
    try {
      this.aggregationType = AggregationType.valueOf(aggregationType.toUpperCase(Locale.ROOT));
    } catch (IllegalArgumentException exception) {
      throw new AggregationTypeNotAllowedException(aggregationType);
    }
  }

  public LocalDateTime getFrom() {
    return from;
  }

  public void setFrom(LocalDateTime from) {
    this.from = from;
  }

  public LocalDateTime getTo() {
    return to;
  }

  public void setTo(LocalDateTime to) {
    this.to = to;
  }


}