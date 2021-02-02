package com.lab9.controller;

import com.lab9.exception.ApiRequestException;
import com.lab9.model.Vehicle;
import com.lab9.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping("/api/products")
    public List<Vehicle> getVehicle() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/api/products/{id}")
    public Vehicle getSingleVehicle(@PathVariable long id) {
        try {
            return  vehicleService.getSingleVehicle(id);
        } catch (NoSuchElementException ex){
            throw new ApiRequestException("Bad ID");
        }
    }

    @PostMapping("api/products")
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle){
        return vehicleService.addVehicle(newVehicle);
    }

    @DeleteMapping("api/products/{id}")
    public void removeVehicle( @PathVariable long id){
        try {
            vehicleService.removeVehicle(id);
        } catch (EmptyResultDataAccessException ex){
            throw new ApiRequestException("Bad ID");
        }
    }

    @GetMapping("api/fulfillment/{id}/products")
    public List<Vehicle> getVehiclesInSalon(@PathVariable long id){

        return vehicleService.getVehiclesInSalon(id);
    }

    @GetMapping("api/fulfillment/{id}/fill")
    public String percentageFill(@PathVariable long id){
        return vehicleService.percentageFill(id);
    }

    @GetMapping("api/product/csv")
    public void exportVehicle(HttpServletResponse response) throws IOException {
        vehicleService.exportVehicle(response);
    }


}
