package com.lab9.controller;

import com.lab9.exception.ApiRequestException;
import com.lab9.model.Rating;
import com.lab9.model.Vehicle;
import com.lab9.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RatingController {

    @Autowired
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/api/ratings")
    public List<Rating> getRating() {
        return ratingService.getRatings();
    }

    @GetMapping("/api/ratings/{id}")
    public Rating getSingleRating(@PathVariable long id) {
        try {
            return  ratingService.getSingleRating(id);
        } catch (NoSuchElementException ex){
            throw new ApiRequestException("Bad ID");
        }
    }

    @PostMapping("api/rating")
    public Rating addRating(@RequestBody Rating newRating) {
        return ratingService.addRating(newRating);
    }

    @GetMapping("/api/product/{id}/rating")
    public double getAvgProduct(@PathVariable long id){
        try {
            return  ratingService.getAvgProduct(id);
        } catch (NullPointerException ex){
            throw new ApiRequestException("Bad ID");
        }
    }

    @GetMapping("/api/fulfillment/{id}/rating")
    public double getAvgSalon(@PathVariable long id){
        try {
            return  ratingService.getAvgSalon(id);
        } catch (NullPointerException ex){
            throw new ApiRequestException("Bad ID");
        }

    }
}
