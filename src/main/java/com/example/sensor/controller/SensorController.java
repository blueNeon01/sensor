package com.example.sensor.controller;

import com.example.sensor.dynamo.domain.SensorLogDomain;
import com.example.sensor.dynamo.respository.SensorLogRepository;
import com.example.sensor.requests.SaveSensorLogRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorController {

  private static final Logger logger = LogManager.getLogger(SensorController.class);

  @Autowired
  private SensorLogRepository sensorLogRepository;

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
      aggregationGenerated = true;
    } catch (Exception exception) {
      logger.info(exception.getMessage());
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
      logger.info("Adding logs");
      return true;
    } catch (Exception exception) {
      logger.info(exception.getMessage());
      return false;
    }

  }
}
