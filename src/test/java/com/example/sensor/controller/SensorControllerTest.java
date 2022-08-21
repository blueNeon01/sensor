package com.example.sensor.controller;


import com.example.sensor.dynamo.respository.SensorLogRepository;
import com.example.sensor.requests.SaveSensorLogRequest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class )
public class SensorControllerTest {

  @InjectMocks
  private SensorController sensorController;

  @Mock
  private SensorLogRepository sensorLogRepository;

  @Test
  public void testSave() throws ParseException {

    final List<SaveSensorLogRequest> requestList = new ArrayList<>();
    requestList.add(createRequest());

    sensorController.save(requestList);

    Mockito.verify(sensorLogRepository, Mockito.times(1)).save(Mockito.any());
  }

  private SaveSensorLogRequest createRequest(){
    final SaveSensorLogRequest request = new SaveSensorLogRequest();
    request.setTemperature(10F);
    request.setWhen(LocalDateTime.now());
    return request;
  }

}
