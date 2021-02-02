package com.lab9.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
public class Carshowroom {
    @Id
    @GeneratedValue
    @JsonProperty
    private long id_carshowroom;
    private String salonname;
    private int maxcars;
    //private int currentCarsAmount;
    //List<Vehicle> carList = new LinkedList<>();
    //Map<String, Integer> nameAmountMap = new TreeMap<String, Integer>();

    @OneToMany(mappedBy = "id_rating",cascade=CascadeType.ALL)
    List<Rating> Rating;

    @OneToMany(mappedBy = "id_vehicle",cascade = CascadeType.ALL)
    List<Vehicle> Vehicle;

    public Carshowroom(long id_carshowroom, String salonname, int maxcars) {
        this.id_carshowroom = id_carshowroom;
        this.salonname = salonname;
        this.maxcars = maxcars;
        //this.currentCarsAmount = 0;
    }

    public long getId_carshowroom() {
        return id_carshowroom;
    }

    public Carshowroom() {

    }

    public int getMaxcars() {
        return maxcars;
    }

    public String getSalonname() {
        return salonname;
    }

    public void setId_carshowroom(long id_carshowroom) {
        this.id_carshowroom = id_carshowroom;
    }

    public void setSalonname(String salonname) {
        this.salonname = salonname;
    }

    public void setMaxcars(int maxcars) {
        this.maxcars = maxcars;
    }

    public void setRating(List<com.lab9.model.Rating> rating) {
        Rating = rating;
    }

    public void setVehicle(List<com.lab9.model.Vehicle> vehicle) {
        Vehicle = vehicle;
    }
}

