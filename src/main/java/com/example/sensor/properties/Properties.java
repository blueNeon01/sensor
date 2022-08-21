package com.example.sensor.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("sensor")
public class Properties {

  private String awsRegion;
  private Boolean sqsEnabled;
  private String awsSqsEndpoint;
  private String awsAccessKey;
  private String awsSecretKey;
  private String awsDynamoEndpoint;

}

