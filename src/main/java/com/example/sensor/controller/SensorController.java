package com.example.sensor.controller;

import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.example.sensor.dynamo.domain.SensorLogDomain;
import com.example.sensor.dynamo.respository.SensorLogAggregatedRepository;
import com.example.sensor.dynamo.respository.SensorLogRepository;
import com.example.sensor.enums.AggregationType;
import com.example.sensor.requests.ListSensorLogsRequest;
import com.example.sensor.requests.SaveSensorLogRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorController {

  @Autowired
  private SensorLogRepository sensorLogRepository;

  @Autowired
  private SensorLogAggregatedRepository sensorLogAggregatedRepository;

//  @GetMapping(path = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
//  List<? extends SensorLogAggregatedDomain> list(
//      @RequestBody ListSensorLogsRequest listSensorLogs) {
//
//    var from = listSensorLogs.getFrom();
//    var to = listSensorLogs.getTo();
//
//    if (listSensorLogs.getAggregationType().equals(AggregationType.DAILY)) {
//      return sensorLogAggregatedRepository.listSensorLogDailyAggregated(from, to);
//    }
//
//    return sensorLogAggregatedRepository.listSensorLogHourlyAggregated(from, to);
//
//  }

  @GetMapping(path = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
  List<? extends ScanRequest> list(
      @RequestBody ListSensorLogsRequest listSensorLogs) {

    var from = listSensorLogs.getFrom();
    var to = listSensorLogs.getTo();

    if (listSensorLogs.getAggregationType().equals(AggregationType.DAILY)) {
      return sensorLogRepository.listAll(from, to);
    }

    return sensorLogRepository.listAll(from, to);

  }

  @PostMapping(path = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
  Map<String, Object> save(@RequestBody List<SaveSensorLogRequest> payload) throws ParseException {

    Map<String, Object> result = new HashMap<>();

    int logsToSave = payload.size();
    int logsSaved = 0;
    boolean aggregationGenerated = false;
    for (SaveSensorLogRequest entry : payload) {
      if (this.projectSensorLog(entry)) {
        logsSaved++;
      }
    }

    try {
      //this.sensorLogAggregatorBuilder.build();
      aggregationGenerated = true;
    } catch (Exception exception) {
      //Logger.exception("Unable to generate aggregation of sensor logs", exception);
      System.out.println("teste");
    }

    result.put("logsToSave", logsToSave);
    result.put("logsSaved", logsSaved);
    result.put("aggregationGenerated", aggregationGenerated);

    return result;
  }

  private boolean projectSensorLog(SaveSensorLogRequest payload) throws ParseException {

    SensorLogDomain log = payload.toSensorLog();
    log.setWhen(payload.getWhen());
    log.setTemperature(payload.getTemperature());

    try {
      sensorLogRepository.save(log);
      //this.sensorLogAggregatorBuilder.add(log.getWhen());
      System.out.println("Adicionou no banco");
      return true;
    } catch (Exception exception) {
      //Logger.exception("Unable to save sensor log", exception);
      System.out.println(exception.getMessage());
      return false;
    }

  }
}
