package com.lab9.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vehicle  {
    @Id
    @GeneratedValue
    @JsonProperty
    private long id_vehicle;
    private String brand;
    private String model;
    private transient ItemConditions condition;
    private int production_year;
    private double mileage;
    private double enginesize;
    private double price;

    @OneToMany(mappedBy = "id_rating", cascade=CascadeType.ALL)
    List<Rating> Rating;

    @ManyToOne//(cascade=CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_carshowroom")
    //@JoinColumn(name = "carShowroom_id")
    Carshowroom carshowroom;

    public Carshowroom getCarshowroom() {
        return carshowroom;
    }

    public void setCarshowroom(Carshowroom carshowroom) {
        this.carshowroom = carshowroom;
    }

    public Vehicle() {
    }

    public long getId_vehicle() {
        return id_vehicle;
    }

    public Vehicle(String brand, String model, ItemConditions condition, int production_year, double mileage, double enginesize, double price) {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.production_year = production_year;
        this.mileage = mileage;
        this.enginesize = enginesize;
        this.price=price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setId(long id) {
        this.id_vehicle = id;
    }

    public long getId() {
        return id_vehicle;
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

    public double getEnginesize() {
        return enginesize;
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
        System.out.println("EngineSize: " + this.enginesize);
        System.out.println("Price: " + this.price);

    }

}
