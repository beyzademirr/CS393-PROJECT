package com.example.Project393.Repository;

import com.example.Project393.Model.Equipment;
import org.springframework.data.jpa.repository.*;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    @Query("select e from Equipment e where e.id=?1")
    Equipment findByCode(int equipmentCode);
    @Query("select e from Equipment e where e.name=?1")
    Equipment findByName(String s);
}
