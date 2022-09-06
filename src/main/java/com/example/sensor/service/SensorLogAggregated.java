package com.example.sensor.service;

import com.example.sensor.dynamo.domain.SensorLogAggregatedDomain;
import com.example.sensor.dynamo.respository.SensorLogAggregatedRepository;
import com.example.sensor.enums.AggregationType;
import com.example.sensor.requests.SaveSensorLogRequest;
import com.example.sensor.util.Util;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorLogAggregated {

  @Autowired
  private SensorLogAggregatedRepository sensorLogAggregatedRepository;

  private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(
      SensorLogAggregated.class);

  public void sensorLogAggregated(List<SaveSensorLogRequest> requests) {

    final Map<String, List<SaveSensorLogRequest>> mapAggregatedSensorHourly = getStringListMap(
        requests, ChronoUnit.HOURS);
    mapToDomain(mapAggregatedSensorHourly, AggregationType.HOURLY);

    final Map<String, List<SaveSensorLogRequest>> mapAggregatedSensorDaily = getStringListMap(
        requests, ChronoUnit.DAYS);
    mapToDomain(mapAggregatedSensorDaily, AggregationType.DAILY);

  }

  private static Map<String, List<SaveSensorLogRequest>> getStringListMap(
      List<SaveSensorLogRequest> requests, ChronoUnit chronoUnit) {
    final Map<String, List<SaveSensorLogRequest>> mapAggregatedSensor = new HashMap<>();

    try {
      for (SaveSensorLogRequest request : requests) {
        mapAggregatedSensor.computeIfAbsent(
            request.getWhen().truncatedTo(chronoUnit).toString(), k -> new ArrayList<>());
        mapAggregatedSensor.get(request.getWhen().truncatedTo(chronoUnit).toString())
            .add(request);
      }

    } catch (Exception e) {
      logger.info(e.getMessage());
    }
    return mapAggregatedSensor;
  }

  private SensorLogAggregatedDomain mapToDomain(
      Map<String, List<SaveSensorLogRequest>> mapAggregatedSensor, AggregationType aggregationType) {
    var aggregatedSensorList = mapAggregatedSensor.entrySet().iterator().next().getValue();

    float sumLogs = 0F;
    int countLogs = 0;

    for (SaveSensorLogRequest saveSensorLogRequest : aggregatedSensorList) {
      sumLogs += saveSensorLogRequest.getTemperature();
      countLogs += 1;
    }

    float averageLogs = sumLogs / countLogs;

    SensorLogAggregatedDomain domain = new SensorLogAggregatedDomain();
    domain.setWhen(Util.dateFormatter(mapAggregatedSensor.keySet().toString()));
    domain.setAverageTemperature(averageLogs);
    domain.setTotalRecords(countLogs);
    domain.setAggregationType(aggregationType.toString());

    sensorLogAggregatedRepository.save(domain);
    return domain;

  }

}

