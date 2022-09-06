package com.example.sensor.dynamo.respository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.sensor.dynamo.domain.SensorLogDomain;
import com.example.sensor.dynamo.mappers.SensorLogMapper;
import com.example.sensor.dynamo.model.SensorLogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SensorLogRepository {

  @Autowired
  private DynamoDBMapper dynamoDBMapper;

  public void save(SensorLogDomain sensorLogDomain){
    SensorLogModel sensorLogModel = SensorLogMapper.INSTANCE.mapFrom(sensorLogDomain);
    dynamoDBMapper.save(sensorLogModel);
  }

}
