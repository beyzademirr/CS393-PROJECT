package com.example.Project393.Repository;

import com.example.Project393.Model.Equipment;
import com.example.Project393.Model.Reservation;
import com.example.Project393.Model.Service;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r where r.id=?1")
    Reservation findByID(int id);

    @Query("select r from Reservation r where r.number=?1")
    Reservation findByNumber(String resNum);

    @Query("select r.services from Reservation r where r.id=?1")
    List<Service> findService(int id);

    @Query("select rs from Reservation r inner join r.services rs where r.number=?1 and rs.id=?2")
    Service findS(String resNum, int serviceCode);

    @Query("select r.equipments from Reservation r where r.id=?1")
    List<Equipment> findEquipment(int id);

    @Query("select rs from Reservation r inner join r.equipments rs where r.number=?1 and rs.id=?2")
    Equipment findE(String resNum, int equipmentCode);
}
