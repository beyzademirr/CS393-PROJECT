package com.example.Project393.Repository;

import com.example.Project393.Model.Car;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("select c from Car c where c.type = ?1 and c.transmissionType=?2 and c.status=?3")
    ArrayList<Car> searchByAvailability(String c, String t, String s);

    @Modifying
    @Query("delete from Car c where c.barcode = ?1")
    @Transactional
    void deleteByBarcode(String barcode);

    @Query("select c from Car c where c.barcode = ?1")
    Car findByBarcode(String barcode);

    @Query("select c from Car c where c.status = ?1")
    List<Car> findAllRented(String loaned);


}
