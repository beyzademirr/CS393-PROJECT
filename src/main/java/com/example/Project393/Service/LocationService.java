package com.example.Project393.Service;

import com.example.Project393.Model.Location;
import com.example.Project393.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public void saveLocation (Location location){
        locationRepository.save(location);
    }

    public void deleteLocation(Location location){
        locationRepository.delete(location);
    }

    public void deleteAll(){
        locationRepository.deleteAll();
    }

    public List<Location> readAll(){
        return locationRepository.findAll();
    }

    public Location readOne(int code){
        return locationRepository.findByCode(code);
    }
}
