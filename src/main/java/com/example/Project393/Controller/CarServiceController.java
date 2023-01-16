package com.example.Project393.Controller;
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
@RequestMapping(value = "/services")
public class CarServiceController {
    @Autowired
    CarService carService;

    @Autowired
    ReservationService reservationService;

    @PutMapping(value = "/{code}/reservations/{number}")
    @Operation(
            summary = "Add service",
            description = "Add service to reservation"
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
                            description = "Service is not found"

                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Error has occurred"

                    ),
            }
    )
    public ResponseEntity<Boolean> addService(@PathVariable("code") int serviceCode,@PathVariable("number") String resNum){
        try{
            Boolean isOk=reservationService.addService(resNum,serviceCode);
            if(!isOk) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }

    }
}
