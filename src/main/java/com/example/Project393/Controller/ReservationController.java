package com.example.Project393.Controller;
import com.example.Project393.Model.Car;
import com.example.Project393.Model.DTO.CarDTO;
import com.example.Project393.Model.DTO.RentedCarDTO;
import com.example.Project393.Model.DTO.ReservationDTO;
import com.example.Project393.Model.Equipment;
import com.example.Project393.Model.Reservation;
import com.example.Project393.Service.CarService;
import com.example.Project393.Service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    @Autowired
    CarService carService;

    @Autowired
    ReservationService reservationService;

    @PostMapping
    @Operation(
            summary = "Make Reservation",
            description = "Make Reservation"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = ReservationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "206",
                            description = "Car is not available"

                    ),
            }
    )
    public ResponseEntity<ReservationDTO> makeReservation (String barcode, int dayCount, int memberId, int pickUpCode, int dropOffCode

                                                         ,ArrayList<String> equipmentNames, ArrayList<String> serviceNames){
        boolean isAvailable = carService.isAvailable(barcode);

        if(!isAvailable) return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(null);

        ReservationDTO r = reservationService.makeReservationWithName(barcode, dayCount, memberId, pickUpCode, dropOffCode, equipmentNames, serviceNames);

        return  ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @PutMapping(value = "/{number}")
    @Operation(
            summary = "Cancel Reservation",
            description = "Return car with reservation number"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = Boolean.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Reservation not found"

                    ),


                    @ApiResponse(
                            responseCode = "500",
                            description = "Error has occurred"

                    ),
            }
    )
    public ResponseEntity<Boolean> cancelReservation(@PathVariable("number") String resNUm){
        try{
            Reservation reservation = reservationService.findRes(resNUm);
            if(reservation==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            else {
                Car car = reservation.getCar();
                if(reservation.getCar()==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
                reservationService.cancelReservation(resNUm);

                return ResponseEntity.status(HttpStatus.OK).body(true);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
