package com.example.sensor.dynamo.mappers;

import com.example.sensor.dynamo.domain.SensorLogAggregatedDomain;
import com.example.sensor.dynamo.model.SensorLogAggregatedModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class SensorLogAggregatedMapper {

  public static final SensorLogAggregatedMapper INSTANCE = Mappers.getMapper(
      SensorLogAggregatedMapper.class);

  //@Mapping(target = "id", source = "id")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "when", source = "when")
  @Mapping(target = "averageTemperature", source = "averageTemperature")
  @Mapping(target = "totalRecords", source = "totalRecords")

  public abstract SensorLogAggregatedModel mapFrom(final SensorLogAggregatedDomain source);

  public abstract List<SensorLogAggregatedDomain> mapFrom(
      final List<SensorLogAggregatedModel> source);


}
