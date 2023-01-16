package com.example.Project393.Repository;

import com.example.Project393.Model.Service;
import org.springframework.data.jpa.repository.*;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

    @Query("select s from Service s where s.id=?1")
    com.example.Project393.Model.Service findByCode(int serviceCode);
    @Query("select s from Service s where s.name=?1")
    Service findByName(String s);
}
