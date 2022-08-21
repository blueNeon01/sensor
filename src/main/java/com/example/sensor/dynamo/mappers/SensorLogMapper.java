package com.example.sensor.dynamo.mappers;

import com.example.sensor.dynamo.domain.SensorLogDomain;
import com.example.sensor.dynamo.model.SensorLogModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class SensorLogMapper {

  public static  final SensorLogMapper INSTANCE = Mappers.getMapper(SensorLogMapper.class);
  @Mapping(target = "id", ignore = true)
  //@Mapping(target = "id_sensor", source = "id_sensor")
  //@Mapping(target = "id", source = "id")
  @Mapping(target = "when", source = "when")
  @Mapping(target = "temperature", source = "temperature")

  public abstract SensorLogModel mapFrom(final SensorLogDomain source);

  public abstract List<SensorLogDomain> mapFrom(
      final List<SensorLogModel> source);

}