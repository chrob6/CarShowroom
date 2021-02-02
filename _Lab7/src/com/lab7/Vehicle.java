package com.lab7;
import com.lab7.ItemConditions;

import java.io.Serializable;

public class Vehicle  implements Serializable {
    private String brand;
    private String model;
    private transient ItemConditions condition;
    private int production_year;
    private double mileage;
    private double engineSize;
    private double price;

    public Vehicle(String brand, String model, ItemConditions condition, int production_year, double mileage, double engineSize,double price) {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.production_year = production_year;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.price=price;
    }


    public String getModel() {
        return model;
    }

    public int getProduction_year() {
        return production_year;
    }

    public double getMileage() {
        return mileage;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public String getBrand() {
        return brand;
    }

    public ItemConditions getCondition() {
        return condition;
    }

    public double getPrice(){return price;}

    public void print(){
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Condition: " + this.condition);
        System.out.println("Production Year: " + this.production_year);
        System.out.println("Mileage(in km): " + this.mileage);
        System.out.println("EngineSize: " + this.engineSize);
        System.out.println("Price: " + this.price);

    }

}
