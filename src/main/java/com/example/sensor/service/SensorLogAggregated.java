package com.example.sensor.service;

import com.example.sensor.dynamo.domain.SensorLogAggregatedDomain;
import com.example.sensor.dynamo.respository.SensorLogAggregatedRepository;
import com.example.sensor.requests.SaveSensorLogRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorLogAggregated {

  @Autowired
  private SensorLogAggregatedRepository sensorLogAggregatedRepository;

  private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(
      SensorLogAggregated.class);
  public SensorLogAggregatedDomain sensorLogAggregated(List<SaveSensorLogRequest> requests) {

    final Map<String, List<SaveSensorLogRequest>> mapAggregatedSensor = new HashMap<>();

    try {
      for (SaveSensorLogRequest request : requests){
        mapAggregatedSensor.computeIfAbsent(request.getWhen().truncatedTo(ChronoUnit.HOURS).toString(), k -> new ArrayList<>());
        mapAggregatedSensor.get(request.getWhen().truncatedTo(ChronoUnit.HOURS).toString()).add(request);
      }

      }  catch (Exception e){
      logger.info(e.getMessage());
    }

    var retorno = mapToDomain(mapAggregatedSensor);

    return null;

  }

  private SensorLogAggregatedDomain mapToDomain(Map<String, List<SaveSensorLogRequest>> mapAggregatedSensor) {
    var teste = mapAggregatedSensor.entrySet().iterator().next().getValue();

    float sumLogs = 0F;
    int count = 0;

    for (SaveSensorLogRequest saveSensorLogRequest : teste) {
      sumLogs += saveSensorLogRequest.getTemperature();
      count += 1;
    }

    float averageLogs = sumLogs / count;


    System.out.println("printando average " + sumLogs + " printando count " + count);

    SensorLogAggregatedDomain domain = new SensorLogAggregatedDomain();
    domain.setId(UUID.randomUUID());
    domain.setWhen(LocalDateTime.now());
    domain.setAverageTemperature(averageLogs);
    domain.setTotalRecords(count);

    return domain;
    //sensorLogAggregatedRepository.save(domain);

  }

}

