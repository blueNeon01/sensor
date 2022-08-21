package com.example.sensor.dynamo.respository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.example.sensor.dynamo.domain.SensorLogDomain;
import com.example.sensor.dynamo.mappers.SensorLogMapper;
import com.example.sensor.dynamo.model.SensorLogModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

//  public List<SensorLogDomain> listAll(LocalDateTime from,
//      LocalDateTime to) {
//
//    final HashMap<String, AttributeValue> values = new HashMap<>();
//    values.put(":id_sensor", new AttributeValue().withS("ca274d7b-e284-4f81-a7a0-b4a54f02ae7d"));
//    values.put(":from", new AttributeValue().withS(from.toString()));
//    values.put(":to", new AttributeValue().withS(to.toString()));
//
//    final DynamoDBQueryExpression<SensorLogModel> queryExpression = new DynamoDBQueryExpression<SensorLogModel>()
//        .withKeyConditionExpression("id_sensor = :id_sensor AND when_log BETWEEN :from AND :to")
//        .withExpressionAttributeValues(values)
//        .withConsistentRead(false);
//
//    final List<SensorLogModel> query = dynamoDBMapper.query(
//        SensorLogModel.class, queryExpression);
//
//    if (query.isEmpty()) {
//      return Collections.emptyList();
//    }
//
//    return SensorLogMapper.INSTANCE.mapFrom(query);
//
//  }

  public List<ScanRequest> listAll(LocalDateTime from,
      LocalDateTime to) {

    final HashMap<String, AttributeValue> values = new HashMap<>();
    values.put("from", new AttributeValue().withS(from.toString()));
    values.put("to", new AttributeValue().withS(to.toString()));


    var condition = new Condition();
    condition.withComparisonOperator(ComparisonOperator.BETWEEN);
    condition.withAttributeValueList(values.get("from"), values.get("to"));


    ScanRequest scanRequest = new ScanRequest().withTableName("tb_sensor_log");
    scanRequest.addScanFilterEntry("when_log", condition);

    List<ScanRequest> scanRequestList = new ArrayList<>();
    scanRequestList.add(scanRequest);

    return scanRequestList;

  }
}
