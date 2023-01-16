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
import java.util.List;


@RestController
@RequestMapping(value = "/cars")
public class CarController {
    @Autowired
    CarService carService;

    @Autowired
    ReservationService reservationService;



    @GetMapping(value = "/{car-type}/{transmission-type}")
    @Operation(
            summary = "Find Available Cars",
            description = "Returns available cars"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = CarDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cars Not Found"

                    ),
            }
    )
    public ResponseEntity<ArrayList<CarDTO>> searchCars(@PathVariable("car-type")String type, @PathVariable("transmission-type") String transmissionType){

        ArrayList<CarDTO> cars = carService.searchAvailableCars(type,transmissionType);



        if(cars.size()==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping(value="/rented-cars")
    @Operation(
            summary = "Find Rented Cars",
            description = "Returns rented cars"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = CarDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No rented cars found"

                    ),
            }
    )
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars(){

        List<RentedCarDTO> cars = carService.findRentedCars();

        if(cars.size()==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @DeleteMapping(value = "/{barcode}")
    @Operation(
            summary = "Delete car",
            description = "Delete car without deleting related objects"
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
                            description = "Car is not found"

                    ),

                    @ApiResponse(
                            responseCode = "406",
                            description = "Car is not available"

                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Error has occurred"

                    ),
            }
    )
    public ResponseEntity<Boolean> deleteCar(@PathVariable("barcode") String barcode){
        try{
        Car car = carService.findByBarcode(barcode);
        if(car==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        else if(car.getStatus().equals("AVAILABLE")&&car.getReservation()==null) {
            carService.deleteCar(barcode);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }

        else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(false); }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PutMapping(value = "/reservations/{number}")
    @Operation(
            summary = "Return car",
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
                            description = "Car not found"

                    ),


                    @ApiResponse(
                            responseCode = "500",
                            description = "Error has occurred"

                    ),
            }
    )
    public ResponseEntity<Boolean> returnCar(@PathVariable("number") String resNUm){
        try{
            Reservation reservation = reservationService.findRes(resNUm);
            if(reservation==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            else {
                Car car = reservation.getCar();
                if(reservation.getCar()==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
                carService.returnCar(resNUm);

                return ResponseEntity.status(HttpStatus.OK).body(true);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }


}
