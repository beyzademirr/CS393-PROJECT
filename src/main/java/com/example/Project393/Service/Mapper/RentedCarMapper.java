package com.example.Project393.Service.Mapper;
import com.example.Project393.Model.DTO.CarDTO;
import com.example.Project393.Model.Car;
import com.example.Project393.Model.DTO.RentedCarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentedCarMapper {

    RentedCarMapper INSTANCE = Mappers.getMapper(RentedCarMapper.class);

    @Mapping(source = "barcode", target = "barcode")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "transmissionType", target = "transmissionType")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "reservation.number", target = "resNum")
    @Mapping(source = "reservation.dropOffDate", target = "dropOffDate")
    @Mapping(source = "reservation.dropOffLocation", target = "dropOffLocation")
    @Mapping(source = "member.name", target = "memberName")
    @Mapping(expression = "java(30)", target = "dayCount")
    RentedCarDTO carToRentedCarDTO(Car car);





}
