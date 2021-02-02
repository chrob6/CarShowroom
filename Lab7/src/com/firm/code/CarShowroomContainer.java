package com.firm.code;

import com.firm.code.CarShowroom;

import java.io.Serializable;
import java.util.*;

public class CarShowroomContainer implements Serializable {
    Map<String, CarShowroom> salons = new TreeMap<>();

    public void addCenter(String name, int amount) {
        CarShowroom carShowroom = new CarShowroom(name, amount);
        salons.put(name, carShowroom);
    }


    void removeCenter(String name) {
        Iterator<Map.Entry<String,CarShowroom>> iter = salons.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String,CarShowroom> entry = iter.next();
            if(name.equalsIgnoreCase(entry.getKey())){
                iter.remove();
            }
        }
    }

    List<String> findEmpty(){
        List<String> empty_stores = new LinkedList<>();
        for(Map.Entry<String,CarShowroom> entry : salons.entrySet()) {
            //String key = entry.getKey();
            if (entry.getValue().getCurrentCarsAmount() == 0) {
                empty_stores.add(entry.getKey());
            }
        }
        return empty_stores;
    }

    void summary() {
        double percent;
        for (String key : salons.keySet()) {
            CarShowroom temp = salons.get(key);
            double a = temp.getCurrentCarsAmount();
            double b = temp.getMaxCars();

            percent = (a/b)*100;
            System.out.println(key + " : " + percent + " % fill");
            //System.out.println(salons.get(key));
        }
    }



    public void insertPrevSalon(CarShowroom s) {
            salons.put("salon",s);
    }
}
