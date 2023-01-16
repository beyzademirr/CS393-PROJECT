package com.example.Project393.Service.Mapper;
import com.example.Project393.Model.DTO.ReservationDTO;
import com.example.Project393.Model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "number", target = "number")
    @Mapping(source = "dropOffDate", target = "dropOffDate")
    @Mapping(source = "pickUpDate", target = "pickUpDate")
    @Mapping(source = "dropOffLocation.name", target = "dropOffLocationName")
    @Mapping(source = "pickUpLocation.name", target = "pickUpLocationName")
    @Mapping(source = "dropOffLocation.code", target = "dropOffLocationCode")
    @Mapping(source = "pickUpLocation.code", target = "pickUpLocationCode")
    @Mapping(constant = "5", target = "amount")
    ReservationDTO reservationToReservationDTO(Reservation reservation);
}
