package com.example.sensor.dynamo.respository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.sensor.dynamo.domain.SensorLogAggregatedDomain;
import com.example.sensor.dynamo.mappers.SensorLogAggregatedMapper;
import com.example.sensor.dynamo.model.SensorLogAggregatedModel;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SensorLogAggregatedRepository {

  @Autowired
  private DynamoDBMapper dynamoDBMapper;

  public void save(SensorLogAggregatedDomain sensorLogAggregatedDomain) {
    SensorLogAggregatedModel sensorLogAggregatedModel = SensorLogAggregatedMapper.INSTANCE.mapFrom(
        sensorLogAggregatedDomain);
    dynamoDBMapper.save(sensorLogAggregatedModel);
  }

  public List<SensorLogAggregatedDomain> listSensorLogDailyAggregated(LocalDateTime from,
      LocalDateTime to) {

    final HashMap<String, AttributeValue> values = new HashMap<>();
    values.put(":id_sensor", new AttributeValue().withS(""));
    values.put(":from", new AttributeValue().withS(from.toString()));
    values.put(":to", new AttributeValue().withS(to.toString()));

    final DynamoDBQueryExpression<SensorLogAggregatedModel> queryExpression = new DynamoDBQueryExpression<SensorLogAggregatedModel>()
        .withIndexName(SensorLogAggregatedModel.INDEX_BY_DATE)
        .withKeyConditionExpression("when_log BETWEEN :from AND :to")
        .withExpressionAttributeValues(values)
        .withConsistentRead(false);


    final List<SensorLogAggregatedModel> query = dynamoDBMapper.query(
        SensorLogAggregatedModel.class, queryExpression);

    if (query.isEmpty()) {
      return Collections.emptyList();
    }

    return SensorLogAggregatedMapper.INSTANCE.mapFrom(query);

  }

}
