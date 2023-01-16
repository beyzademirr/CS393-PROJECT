package com.example.Project393.Service;

import com.example.Project393.Model.*;
import com.example.Project393.Model.DTO.ReservationDTO;
import com.example.Project393.Repository.*;
import com.example.Project393.Service.Mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    ServiceRepository serviceRepository;



    public ReservationDTO makeReservation(String barcode, int dayCount, int memberId, int pickUpCode, int dropOffCode,
                                          ArrayList<Equipment> equipments, ArrayList<com.example.Project393.Model.Service> services){
        Car car = carRepository.findByBarcode(barcode);

        if(equipments!=null)
        equipmentRepository.saveAll(equipments);

        if(services!=null)
        serviceRepository.saveAll(services);

        if(car.getStatus().equals("AVAILABLE")){
        Location dropOff = locationRepository.findByCode(dropOffCode);
        Location pickUp = locationRepository.findByCode(pickUpCode);
        Member member = memberRepository.findByID(memberId);
        Random rnd = new Random();
        int number = 10000000 + rnd.nextInt() * 90000000;

        Reservation reservation = new Reservation();

            Date tod = new Date();
            long seconds = dayCount*60*60*24;
            Date drop = Date.from(tod.toInstant().plusSeconds(seconds));

        car.setStatus("LOANED");
        car.setMember(member);
        reservation.setCreateDate(new Date());
        reservation.setCar(car);
        reservation.setDropOffLocation(dropOff);
        reservation.setPickUpLocation(pickUp);
        reservation.setPickUpDate(new Date());
        reservation.setDropOffDate(new Date());
        reservation.setServices(services);
        reservation.setEquipments(equipments);
        reservation.setStatus("ACTIVE");
        reservation.setNumber(String.valueOf(number));
        reservation.setDropOffDate(drop);

        //SAVE CAR VE RES

            carRepository.save(car);
            reservationRepository.save(reservation);




            double price = dayCount*car.getDailyPrice();

            if(equipments!=null){
            for(int i=0;i<equipments.size();i++){
                price+=equipments.get(i).getPrice();
            }}

            if(services!=null){
            for(int i=0;i<equipments.size();i++){
                price+=services.get(i).getPrice();
            }}

            ReservationDTO reservationDTO = ReservationMapper.INSTANCE.reservationToReservationDTO(reservation);
            reservationDTO.setAmount(price);


            return reservationDTO;
        }
        else return null;


    }

    public boolean addService(String resNum, int serviceCode){
        Reservation reservation = reservationRepository.findByNumber(resNum);
        com.example.Project393.Model.Service service =  serviceRepository.findByCode(serviceCode);

        List<com.example.Project393.Model.Service> services=reservationRepository.findService(reservation.getId());

        com.example.Project393.Model.Service service2= reservationRepository.findS(resNum, serviceCode);

        if(service==null) return false;
       else if(service2!=null)return false;
       else{
            services.add(service);
            reservation.setServices(services);
            reservationRepository.save(reservation);
             return true;}
    }

    public boolean addEquipment(String resNum, int equipmentCode){
        Reservation reservation = reservationRepository.findByNumber(resNum);
        Equipment equipment =  equipmentRepository.findByCode(equipmentCode);

        List<Equipment> equipments=reservationRepository.findEquipment(reservation.getId());

      Equipment equipment2= reservationRepository.findE(resNum, equipmentCode);

        if(equipment==null) return false;
        else if(equipment2!=null)return false;
        else{
            equipments.add(equipment);
            reservation.setEquipments(equipments);
            reservationRepository.save(reservation);
            return true;}
    }
    public void deleteAll(){
        reservationRepository.deleteAll();
    }

    public boolean cancelReservation(String resNum){
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


    public Reservation findRes(String resNUm) {
        return reservationRepository.findByNumber(resNUm);
    }

    public List<Reservation> readAll(){
        return reservationRepository.findAll();
    }

    public ReservationDTO makeReservationWithName(String barcode, int dayCount, int memberId, int pickUpCode, int dropOffCode,
                                          ArrayList<String> eqs, ArrayList<String> servs){
        Car car = carRepository.findByBarcode(barcode);

        List<Equipment> equipments = findByEquipmentNames(eqs);
        List<com.example.Project393.Model.Service> services = findByServiceNames(servs);


        if(equipments!=null)
            equipmentRepository.saveAll(equipments);

        if(services!=null)
            serviceRepository.saveAll(services);

        if(car.getStatus().equals("AVAILABLE")){
            Location dropOff = locationRepository.findByCode(dropOffCode);
            Location pickUp = locationRepository.findByCode(pickUpCode);
            Member member = memberRepository.findByID(memberId);
            Random rnd = new Random();
            int number = 10000000 + rnd.nextInt() * 90000000;

            Reservation reservation = new Reservation();

            Date pick = Date.from((new Date()).toInstant().plusSeconds(60*60*24));
            long seconds = dayCount*60*60*24;
            Date drop = Date.from(pick.toInstant().plusSeconds(seconds));

            car.setStatus("LOANED");
            car.setMember(member);
            reservation.setCreateDate(new Date());
            reservation.setCar(car);
            reservation.setDropOffLocation(dropOff);
            reservation.setPickUpLocation(pickUp);
            reservation.setPickUpDate(pick);
            reservation.setDropOffDate(new Date());
            reservation.setServices(services);
            reservation.setEquipments(equipments);
            reservation.setStatus("ACTIVE");
            reservation.setNumber(String.valueOf(number));
            reservation.setDropOffDate(drop);

            //SAVE CAR VE RES

            carRepository.save(car);
            reservationRepository.save(reservation);




            double price = dayCount*car.getDailyPrice();

            if(equipments!=null){
                for(int i=0;i<equipments.size();i++){
                    price+=equipments.get(i).getPrice();
                }}

            if(services!=null){
                for(int i=0;i<equipments.size();i++){
                    price+=services.get(i).getPrice();
                }}

            ReservationDTO reservationDTO = ReservationMapper.INSTANCE.reservationToReservationDTO(reservation);
            reservationDTO.setAmount(price);


            return reservationDTO;
        }
        else return null;


    }

    public List<Equipment> findByEquipmentNames(ArrayList<String> names){
        List<Equipment> list = new ArrayList<>();
        for(int i=0; i<names.size();i++){
            Equipment e = equipmentRepository.findByName(names.get(i));
           if(e!=null) list.add(e);

        }


        return list;
    }

    public List<com.example.Project393.Model.Service> findByServiceNames(ArrayList<String> names){
        List<com.example.Project393.Model.Service> list = new ArrayList<>();
        for(int i=0; i<names.size();i++){
            com.example.Project393.Model.Service s =serviceRepository.findByName(names.get(i));
            if(s!=null) list.add(s);

        }


        return list;
    }
}
