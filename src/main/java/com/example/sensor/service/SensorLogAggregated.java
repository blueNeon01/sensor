package com.example.sensor.service;

import com.example.sensor.dynamo.domain.SensorLogAggregatedDomain;
import com.example.sensor.dynamo.respository.SensorLogAggregatedRepository;
import com.example.sensor.enums.AggregationType;
import com.example.sensor.requests.SaveSensorLogRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    final Map<String, List<SaveSensorLogRequest>> mapAggregatedSensor = new HashMap<>();

    try {
      for (SaveSensorLogRequest request : requests) {
        mapAggregatedSensor.computeIfAbsent(
            request.getWhen().truncatedTo(ChronoUnit.HOURS).toString(), k -> new ArrayList<>());
        mapAggregatedSensor.get(request.getWhen().truncatedTo(ChronoUnit.HOURS).toString())
            .add(request);
      }

    } catch (Exception e) {
      logger.info(e.getMessage());
    }

    mapToDomain(mapAggregatedSensor);

  }

  private SensorLogAggregatedDomain mapToDomain(
      Map<String, List<SaveSensorLogRequest>> mapAggregatedSensor) {
    var teste = mapAggregatedSensor.entrySet().iterator().next().getValue();

    float sumLogs = 0F;
    int countLogs = 0;

    for (SaveSensorLogRequest saveSensorLogRequest : teste) {
      sumLogs += saveSensorLogRequest.getTemperature();
      countLogs += 1;
    }

    float averageLogs = sumLogs / countLogs;

    SensorLogAggregatedDomain domain = new SensorLogAggregatedDomain();
    domain.setWhen(dateFormater(mapAggregatedSensor.keySet().toString()));
    domain.setAverageTemperature(averageLogs);
    domain.setTotalRecords(countLogs);
    domain.setAggregationType(AggregationType.DAILY.toString());

    sensorLogAggregatedRepository.save(domain);

    return domain;

  }

  private LocalDateTime dateFormater(String date) {
    String newDate = date.replaceAll("[()\\[\\]]", "");
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    LocalDateTime dateTime = LocalDateTime.parse(newDate, formatter);

    return dateTime;
  }

}

