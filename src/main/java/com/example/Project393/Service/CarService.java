package com.example.Project393.Service;

import com.example.Project393.Model.Car;
import com.example.Project393.Model.DTO.CarDTO;
import com.example.Project393.Model.DTO.RentedCarDTO;
import com.example.Project393.Model.Reservation;
import com.example.Project393.Repository.CarRepository;
import com.example.Project393.Repository.ReservationRepository;
import com.example.Project393.Service.Mapper.CarMapper;
import com.example.Project393.Service.Mapper.RentedCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public ArrayList<CarDTO> searchAvailableCars(String carType, String transmissionType){
        ArrayList<Car> cars = carRepository.searchByAvailability(carType, transmissionType,"AVAILABLE");
        ArrayList<CarDTO> carDTOs = new ArrayList<>();

        for(int i=0;i<cars.size();i++){
            carDTOs.add(CarMapper.INSTANCE.carToCarDTO(cars.get(i)));
        }

        return carDTOs;
    }

    public void saveCar(Car car){
        carRepository.save(car);
    }



    public boolean deleteCar(String barcode){
        Car car = carRepository.findByBarcode(barcode);
        Reservation reservation = car.getReservation();

        if(car.getStatus().equals("AVAILABLE")&& reservation==null){
            //deleteByBarcode(barcode, car);
            carRepository.delete(car);
            return true;
        }
        else return false;

    }

    public List<RentedCarDTO> findRentedCars(){
        List<Car> cars = carRepository.findAllRented("LOANED");
        List<RentedCarDTO> cars2 = new ArrayList<>() ;
        for(int i =0; i < cars.size();i++) {
            RentedCarDTO rentedCarDTO = RentedCarMapper.INSTANCE.carToRentedCarDTO(cars.get(i));
            Reservation reservation = cars.get(i).getReservation();

            long s = (reservation.getDropOffDate().getTime()-reservation.getPickUpDate().getTime())/ (1000 * 60 * 60 * 24) ;

            rentedCarDTO.setDayCount((int) s);


            cars2.add(rentedCarDTO);}

        return cars2;
    }


    public boolean returnCar(String resNum){
        Reservation reservation = reservationRepository.findByNumber(resNum);

        if(reservation==null) return false;
            Car car = reservation.getCar();
            reservation.setStatus("COMPLETED");
            car.setStatus("AVAILABLE");
            reservation.setDropOffDate(new Date());
            car.setReservation(null);
            reservation.setCar(null);
            carRepository.save(car);
            reservationRepository.save(reservation);
            return true;


    }

    public boolean isAvailable(String barcode) {
        String s = "AVAILABLE";
        Car car = carRepository.findByBarcode(barcode);
        if(car == null) return false;
        return s.equals(car.getStatus());
    }

    public void deleteAll(){
        carRepository.deleteAll();
    }

    public Car findByBarcode(String barcode) {

        return carRepository.findByBarcode(barcode);
    }

    public List<Car> readAll(){
        return carRepository.findAll();
    }
}
