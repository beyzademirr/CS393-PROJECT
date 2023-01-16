package com.example.Project393.Service.Mapper;

import com.example.Project393.Model.DTO.CarDTO;
import com.example.Project393.Model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "barcode", target = "barcode")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "mileage", target = "mileage")
    @Mapping(source = "transmissionType", target = "transmissionType")
    @Mapping(source = "type", target = "type")
    CarDTO carToCarDTO(Car car);
}
