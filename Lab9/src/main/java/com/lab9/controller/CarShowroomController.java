package com.lab9.controller;

import com.lab9.exception.ApiRequestException;
import com.lab9.model.Carshowroom;
import com.lab9.model.Vehicle;
import com.lab9.service.CarShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CarShowroomController {

    @Autowired
    private final CarShowroomService carShowroomService;

    public CarShowroomController(CarShowroomService carShowroomService) {
        this.carShowroomService = carShowroomService;
    }

    @GetMapping("/api/fulfillment")
    public List<Carshowroom> getCarShowroom() {
        return carShowroomService.getCarShowroom();
    }

    @GetMapping("/api/fulfillment/{id}")
    public Carshowroom getSingleCarShowroom(@PathVariable long id) {
        try {
            return carShowroomService.getSingleCarShowroom(id);
        } catch (NoSuchElementException ex){
            throw new ApiRequestException("Bad ID");
        }
    }

    @PostMapping("/api/fulfillment")
    public Carshowroom addcarshowroom(@RequestBody Carshowroom newcarshowroom){
        return carShowroomService.addcarshowroom(newcarshowroom);
    }

    @DeleteMapping("/api/fulfillment/{id}")
    public void removeCarshowroom( @PathVariable long id){

        try {
            carShowroomService.removecarShowroomService(id);
        } catch (EmptyResultDataAccessException ex){
            throw new ApiRequestException("Bad ID");
        }
    }

}
