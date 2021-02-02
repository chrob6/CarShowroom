package com.lab9.service;

import com.lab9.model.Carshowroom;
import com.lab9.repository.CarshowroomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarShowroomService {

    private final CarshowroomRepository carShowroomRepository;

    public CarShowroomService(CarshowroomRepository carShowroomRepository) {
        this.carShowroomRepository = carShowroomRepository;
    }


    public Carshowroom getSingleCarShowroom(long id) {
        return carShowroomRepository.findById(id)
                .orElseThrow();
    }

    public List<Carshowroom> getCarShowroom() {
        return carShowroomRepository.findAll();
    }

    public Carshowroom addcarshowroom(Carshowroom newcarshowroom) {
        if(newcarshowroom.getMaxcars() == 0){
            return new Carshowroom();
        }
        if(newcarshowroom.getSalonname() == null){
            return new Carshowroom();
        }
        return carShowroomRepository.save(newcarshowroom);
    }

    public void removecarShowroomService(long id) {
        carShowroomRepository.deleteById(id);
    }
}
