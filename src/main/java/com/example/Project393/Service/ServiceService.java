package com.example.Project393.Service;

import com.example.Project393.Model.Car;
import com.example.Project393.Model.Equipment;
import com.example.Project393.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    public void saveService(com.example.Project393.Model.Service service){
        serviceRepository.save(service);
    }

    public void deleteAll(){
        serviceRepository.deleteAll();
    }

    public List<com.example.Project393.Model.Service> readAll(){
        return serviceRepository.findAll();
    }

    public com.example.Project393.Model.Service readOne(int code){
        return serviceRepository.findByCode(code);
    }

    public List<com.example.Project393.Model.Service> findByNames(ArrayList<String> names){
        List<com.example.Project393.Model.Service> list = new ArrayList<>();
        for(int i=0; i<names.size();i++){
            list.add(serviceRepository.findByName(names.get(i)));

        }


        return list;
    }
}
