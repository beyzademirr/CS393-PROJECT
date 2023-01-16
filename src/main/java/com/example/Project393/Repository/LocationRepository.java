package com.example.Project393.Repository;


import com.example.Project393.Model.Location;
import org.springframework.data.jpa.repository.*;

public interface LocationRepository extends JpaRepository<Location, Integer>{

    @Query("select l from Location l where l.code = ?1")
    Location findByCode(int dropOffCode);
}
