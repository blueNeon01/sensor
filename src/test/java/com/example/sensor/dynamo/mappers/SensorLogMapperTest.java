package com.example.sensor.dynamo.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.example.sensor.dynamo.domain.SensorLogDomain;
import com.example.sensor.dynamo.model.SensorLogModel;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.Test;

public class SensorLogMapperTest {

  private final SensorLogMapper mapper = SensorLogMapper.INSTANCE;

  @Test
  public void testeMapFrom(){
    final SensorLogDomain source = new SensorLogDomain();
    source.setId(UUID.randomUUID());
    source.setWhen(LocalDateTime.now());
    source.setTemperature(23F);

    final SensorLogModel target = mapper.mapFrom(source);

    assertThat(target.getWhen(), equalTo(source.getWhen()));
    assertThat(target.getTemperature(), equalTo(source.getTemperature()));
  }

}
