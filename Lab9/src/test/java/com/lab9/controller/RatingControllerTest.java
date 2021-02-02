package com.lab9.controller;

import com.lab9.model.Carshowroom;
import com.lab9.model.Rating;
import com.lab9.repository.RatingRepository;
import com.lab9.service.CarShowroomService;
import com.lab9.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.supercsv.cellprocessor.ParseDouble;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
class RatingControllerTest {

    @Mock
    private RatingRepository ratingRepository;
    @Mock
    private RatingService ratingService;
    @InjectMocks
    private RatingController ratingController;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() throws Exception {
        System.out.print("Hey there");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }

    @Test
    public void testRatingGet() throws Exception {
        mockMvc.perform(get("/api/ratings"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //  .andExcept(MockMvcResultMatchers.content().string())
    }

    @Test
    public void testRatingPost() throws Exception {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Rating rating = new Rating(4,date, "Describing the car");

        ratingRepository.save(rating);
        Optional<Rating> savedRating = ratingRepository.findById(rating.getId_rating());

        assertNotNull(savedRating);
    }

    @Test
    public void testVahicleRating() throws Exception {
        String uri = "/api/product/16/rating";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testSalonRating() throws Exception {
        String uri = "/api/fulfillment/1/rating";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}