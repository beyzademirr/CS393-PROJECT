package com.example.Project393.Service;

import com.example.Project393.Model.Equipment;
import com.example.Project393.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;

    public void saveEquipment(Equipment equipment){
        equipmentRepository.save(equipment);
    }

    public void deleteAll(){
        equipmentRepository.deleteAll();
    }

    public List<Equipment> readAll(){
        return equipmentRepository.findAll();
    }

    public Equipment readOne(int code){
        return equipmentRepository.findByCode(code);
    }

    public List<Equipment> findByNames(ArrayList<String> names){
        List<Equipment> list = new ArrayList<>();
        for(int i=0; i<names.size();i++){
            list.add(equipmentRepository.findByName(names.get(i)));

        }


        return list;
    }


}
