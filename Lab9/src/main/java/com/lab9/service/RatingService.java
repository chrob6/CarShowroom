package com.lab9.service;

import com.lab9.Database;
import com.lab9.model.Carshowroom;
import com.lab9.model.Rating;
import com.lab9.model.Vehicle;
import com.lab9.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    public Rating getSingleRating(long id) {
        return ratingRepository.findById(id)
                .orElseThrow();
    }

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public Rating addRating(Rating newRating) {
        if(newRating.getCarshowroom() == null && newRating.getVehicle() == null){
            Rating badRating = new Rating();
            badRating.setDescription("Rate salon or vehicle!");
            return badRating;
        }
        return ratingRepository.save(newRating);
    }


    public double getAvgProduct(long id) {
        Database database = new Database();
        return  database.BestVehicle(id);
    }

    public double getAvgSalon(long id) {
        Database database = new Database();
        return  database.BestSalon(id);
    }
}
