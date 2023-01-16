package com.example.Project393;

import com.example.Project393.Model.*;
import com.example.Project393.Model.DTO.CarDTO;
import com.example.Project393.Model.DTO.RentedCarDTO;
import com.example.Project393.Model.DTO.ReservationDTO;
import com.example.Project393.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Project393ApplicationTests {

	@Autowired
	CarService carService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	LocationService locationService;
	@Autowired
	MemberService memberService;
	@Autowired
	ServiceService serviceService;
	@Autowired
	EquipmentService equipmentService;

	Car car = new Car("b","b",8,"g","m",10,"d",10,"e",null,null,"AVAILABLE");
	Member member = new Member("k","x","a","d",1,null);
	Location location = new Location(1,"tavşanlı","x");
	Location location2 = new Location(2,"abd","y");
	Equipment equipment = new Equipment("oyuncak",null,4);
	Service service = new Service("sıcak",4,null);
	Equipment equipment2 = new Equipment("koltuk",null,4);
	Service service3 = new Service("yemek",4,null);

	private void clearAll(){
		reservationService.deleteAll();
		carService.deleteAll();
		memberService.deleteAll();
		locationService.deleteAll();
		equipmentService.deleteAll();
		serviceService.deleteAll();

	}



	@Test
	void searchCarsAndDeleteCar() {

		Car car3 = new Car("n","b",8,"g","m",10,"d",10,"e",null,null,"LOST");
		Car car1 = new Car("a","b",8,"g","m",10,"d",10,"e",null,null,"AVAILABLE");
		Car car2 = new Car("h","b",8,"g","m",10,"d",10,"e",null,null,"AVAILABLE");

		carService.saveCar(car1);
		carService.saveCar(car2);
		carService.saveCar(car3);

		ArrayList<CarDTO> cars = carService.searchAvailableCars(car1.getType(),car1.getTransmissionType());

		assertEquals(cars.size(),2);
		assertEquals(cars.get(0).getBarcode(),car1.getBarcode());
		assertEquals(cars.get(1).getBarcode(),car2.getBarcode());

		//carService.deleteAll();
		boolean b = carService.deleteCar(car3.getBarcode());
		boolean a =carService.deleteCar(car2.getBarcode());
		boolean c = carService.deleteCar(car1.getBarcode());

		ArrayList<CarDTO> cars2 = carService.searchAvailableCars(car1.getType(),car1.getTransmissionType());

		assertEquals(cars2.size(),0);
		assertFalse(b);
		assertTrue(a);
		assertTrue(c);

		clearAll();



	}

	@Test
	public void makeRes(){

		carService.saveCar(car);
		memberService.saveMember(member);
		locationService.saveLocation(location);
		locationService.saveLocation(location2);
		serviceService.saveService(service3);
		serviceService.saveService(service);
		equipmentService.saveEquipment(equipment);
		equipmentService.saveEquipment(equipment2);


		ArrayList<Equipment> equipments = new ArrayList<>();
		ArrayList<Service> services =  new ArrayList<>();

		equipments.add(equipment);
		services.add(service);
		equipments.add(equipment2);
		services.add(service3);

		ReservationDTO reservationDTO = reservationService.makeReservation(car.getBarcode(), 10, member.getId(), location2.getCode(), location.getCode(),
					equipments,services);


		assertEquals(reservationDTO.getDropOffLocationCode(), location.getCode());
		assertEquals(reservationDTO.getDropOffLocationName(), location.getName());
		assertEquals(reservationDTO.getPickUpLocationName(), location2.getName());
		assertEquals(reservationDTO.getPickUpLocationCode(), location2.getCode());
		assertEquals(reservationDTO.getAmount(),116);


		clearAll();


}

	@Test
	public void getCars(){

		carService.saveCar(car);
		memberService.saveMember(member);
		locationService.saveLocation(location);
		locationService.saveLocation(location2);
		serviceService.saveService(service3);
		serviceService.saveService(service);
		equipmentService.saveEquipment(equipment);
		equipmentService.saveEquipment(equipment2);

		ArrayList<Equipment> equipments = new ArrayList<>();
		ArrayList<Service> services =  new ArrayList<>();

		equipments.add(equipment);
		services.add(service);
		equipments.add(equipment2);
		services.add(service3);

		ReservationDTO reservationDTO = reservationService.makeReservation(car.getBarcode(),10, member.getId(), location2.getCode(), location.getCode(),
				equipments,services);

		Car car2 = new Car("x","b",8,"g","m",10,"d",10,"e",null,null,"AVAILABLE");


		carService.saveCar(car2);
	    ReservationDTO reservationDTO2 = reservationService.makeReservation("x",10, member.getId(), location2.getCode(), location.getCode(),
				equipments,services);

		List<RentedCarDTO> rentedCarDTOList = carService.findRentedCars();

		assertEquals(rentedCarDTOList.size(),2);
		clearAll();

	}

	@Test
	public void addService(){

		carService.saveCar(car);
		memberService.saveMember(member);
		locationService.saveLocation(location);
		locationService.saveLocation(location2);
		serviceService.saveService(service3);
		serviceService.saveService(service);
		equipmentService.saveEquipment(equipment);
		equipmentService.saveEquipment(equipment2);

		ArrayList<Equipment> equipments = new ArrayList<>();
		ArrayList<Service> services =  new ArrayList<>();

		equipments.add(equipment);
		services.add(service);


		ReservationDTO reservationDTO = reservationService.makeReservation(car.getBarcode(), 10, member.getId(), location2.getCode(), location.getCode(),
				equipments,services);

		assertTrue(reservationService.addService(reservationDTO.getNumber(),service3.getId()));
		assertEquals(reservationService.addService(reservationDTO.getNumber(),service.getId()),false);

		clearAll();

	}

	@Test
	public void addEquipment(){

		carService.saveCar(car);
		memberService.saveMember(member);
		locationService.saveLocation(location);
		locationService.saveLocation(location2);
		serviceService.saveService(service3);
		serviceService.saveService(service);
		equipmentService.saveEquipment(equipment);
		equipmentService.saveEquipment(equipment2);


		ArrayList<Equipment> equipments = new ArrayList<>();
		ArrayList<Service> services =  new ArrayList<>();

		equipments.add(equipment);
		services.add(service);


		ReservationDTO reservationDTO = reservationService.makeReservation(car.getBarcode(),10, member.getId(), location2.getCode(), location.getCode(),
				equipments,services);

		assertTrue(reservationService.addEquipment(reservationDTO.getNumber(),equipment2.getId()));
		assertEquals(reservationService.addEquipment(reservationDTO.getNumber(),equipment.getId()),false);

		clearAll();
	}

	@Test
	public void returnCar(){
		carService.saveCar(car);
		memberService.saveMember(member);
		locationService.saveLocation(location);
		locationService.saveLocation(location2);

		ReservationDTO reservationDTO = reservationService.makeReservation(car.getBarcode(), 10, member.getId(), location2.getCode(), location.getCode(),
				null,null);

		carService.returnCar(reservationDTO.getNumber());

		Car c = carService.findByBarcode(car.getBarcode());

		assertEquals(c.getStatus(),"AVAILABLE");

		ReservationDTO reservationDTO2 = reservationService.makeReservation(car.getBarcode(), 10, member.getId(), location2.getCode(), location.getCode(),
				null,null);

		c = carService.findByBarcode(car.getBarcode());

		assertEquals(c.getReservation().getNumber(),reservationDTO2.getNumber());

		clearAll();
	}

	@Test
	public void cancelRes(){
		carService.saveCar(car);
		memberService.saveMember(member);
		locationService.saveLocation(location);
		locationService.saveLocation(location2);

		ReservationDTO reservationDTO = reservationService.makeReservation(car.getBarcode(), 10, member.getId(), location2.getCode(), location.getCode(),
				null,null);

		reservationService.cancelReservation(reservationDTO.getNumber());

		Car c = carService.findByBarcode(car.getBarcode());

		assertEquals(c.getStatus(),"AVAILABLE");

		ReservationDTO reservationDTO2 = reservationService.makeReservation(car.getBarcode(), 10, member.getId(), location2.getCode(), location.getCode(),
				null,null);

		c = carService.findByBarcode(car.getBarcode());

		assertEquals(c.getReservation().getNumber(),reservationDTO2.getNumber());

		clearAll();
	}

	@Test
	public void makeRes2(){

		carService.saveCar(car);
		memberService.saveMember(member);
		locationService.saveLocation(location);
		locationService.saveLocation(location2);
		serviceService.saveService(service3);
		serviceService.saveService(service);
		equipmentService.saveEquipment(equipment);
		equipmentService.saveEquipment(equipment2);


		ArrayList<String> equipments = new ArrayList<>();
		ArrayList<String> services =  new ArrayList<>();

		equipments.add(equipment.getName());
		services.add(service.getName());
		equipments.add(equipment2.getName());
		services.add(service3.getName());

		ReservationDTO reservationDTO = reservationService.makeReservationWithName(car.getBarcode(), 10, member.getId(), location2.getCode(), location.getCode(),
				equipments,services);


		assertEquals(reservationDTO.getDropOffLocationCode(), location.getCode());
		assertEquals(reservationDTO.getDropOffLocationName(), location.getName());
		assertEquals(reservationDTO.getPickUpLocationName(), location2.getName());
		assertEquals(reservationDTO.getPickUpLocationCode(), location2.getCode());
		assertEquals(reservationDTO.getAmount(),116);


		clearAll();


	}
}
