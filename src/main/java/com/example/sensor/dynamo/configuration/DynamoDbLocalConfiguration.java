package com.example.sensor.dynamo.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.example.sensor.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Profile("local")
@Configuration
@EnableConfigurationProperties(Properties.class)
public class DynamoDbLocalConfiguration {

  @Autowired
  private Properties properties;

  @Bean
  public AmazonDynamoDB amazonDynamoDB(final Properties properties) {

    final BasicAWSCredentials credentials = new BasicAWSCredentials(properties.getAwsAccessKey(),
        properties.getAwsSecretKey());

    final AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
        credentials);

    return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
        new EndpointConfiguration(properties.getAwsDynamoEndpoint(),
            properties.getAwsRegion())).withCredentials(credentialsProvider).build();
  }

  @Bean
  public DynamoDB dynamoDB(final AmazonDynamoDB amazonDynamoDB) {
    var amazonDb = amazonDynamoDB(properties);
    return new DynamoDB(amazonDb);
  }

  @Bean
  public DynamoDBMapper dynamoDBMapper(final AmazonDynamoDB amazonDynamoDB) {
    return new DynamoDBMapper(amazonDynamoDB);
  }

}
