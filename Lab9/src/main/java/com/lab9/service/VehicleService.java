package com.lab9.service;

import com.lab9.Database;
import com.lab9.model.ItemConditions;
import com.lab9.model.Vehicle;
import com.lab9.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Double.NaN;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;



    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getSingleVehicle(long id) {
        return vehicleRepository.findById(id)
                .orElseThrow();
    }

    public Vehicle addVehicle(Vehicle newVehicle) {
        if(newVehicle.getBrand() == null || newVehicle.getModel() == null){
            Vehicle badVehicle = new Vehicle();
            badVehicle.setBrand("THIS FIELD CAN NOT BE EMPTY OR");
            badVehicle.setModel("THIS FIELD CAN NOT BE EMPTY");
            return  badVehicle;
        }
        return vehicleRepository.save(newVehicle);
    }

    public void removeVehicle(long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> getVehiclesInSalon(long id) {
        Database database = new Database();
        List<Vehicle> vehicles_list = database.vehicleList(id);
        return vehicles_list;
    }

    public String percentageFill(long id) {
        Database database = new Database();
        List<Vehicle> vehicles_list = database.vehicleList(id);
        double currentAmount = vehicles_list.size();
        double maxCars = database.getMaxCars(id);
        double result = (currentAmount/maxCars) * 100;
        String return_ = (result + " %");
        if(return_.equals("NaN %")){
            return "BAD ID";
        }
        return return_;
    }

    public void exportVehicle(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Vehilce ID", "Brand", "Model", "Year", "Milage","EngineSize","Price","Carshowroom"};


        String[] nameMapping = {"id_vehicle", "brand", "model", "production_year",
                "mileage", "enginesize", "price", "carshowroom"
        };


        csvWriter.writeHeader(csvHeader);

        List<Vehicle> list_vehicles = getVehicles();

        try {
            for (Vehicle v : list_vehicles) {
                csvWriter.write(v, nameMapping);
            }
        } catch (IllegalArgumentException e){
            //
        }

        csvWriter.close();
    }
}
