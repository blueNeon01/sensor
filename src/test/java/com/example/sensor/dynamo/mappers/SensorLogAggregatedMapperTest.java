package com.example.sensor.dynamo.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.example.sensor.dynamo.domain.SensorLogAggregatedDomain;
import com.example.sensor.dynamo.model.SensorLogAggregatedModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Test;

public class SensorLogAggregatedMapperTest {

  private final SensorLogAggregatedMapper mapper = SensorLogAggregatedMapper.INSTANCE;

  @Test
  public void testeMapFrom(){
    final SensorLogAggregatedDomain source = new SensorLogAggregatedDomain();
    source.setId(UUID.randomUUID());
    source.setWhen(LocalDateTime.now());
    source.setAverageTemperature(10F);
    source.setTotalRecords(1);

    final SensorLogAggregatedModel target = mapper.mapFrom(source);

    assertThat(target.getWhen(), equalTo(source.getWhen()));
    assertThat(target.getAverageTemperature(), equalTo(source.getAverageTemperature()));
    assertThat(target.getTotalRecords(), equalTo(source.getTotalRecords()));

  }

  @Test
  public void testeMapFromList(){
    final SensorLogAggregatedModel aggregateModel = new SensorLogAggregatedModel();
    aggregateModel.setId(UUID.randomUUID());
    aggregateModel.setWhen(LocalDateTime.now());
    aggregateModel.setAverageTemperature(10F);
    aggregateModel.setTotalRecords(1);

    final List<SensorLogAggregatedModel> sourceList = new ArrayList<>();
    sourceList.add(aggregateModel);

    final List<SensorLogAggregatedDomain> targetList = mapper.mapFrom(sourceList);

    final var target = targetList.get(0);
    final var source = sourceList.get(0);

    assertThat(target.getWhen(), equalTo(source.getWhen()));
    assertThat(target.getAverageTemperature(), equalTo(source.getAverageTemperature()));
    assertThat(target.getTotalRecords(), equalTo(source.getTotalRecords()));

  }
}
