package com.lab9.controller;

import com.lab9.model.ItemConditions;
import com.lab9.model.Rating;
import com.lab9.model.Vehicle;
import com.lab9.repository.VehicleRepository;
import com.lab9.service.CarShowroomService;
import com.lab9.service.VehicleService;
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

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
class VehicleControllerTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private VehicleService vehicleService;
    @InjectMocks
    private VehicleController vehicleController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.print("Hey there");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @Test
    public void testVehicleGet() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testVehicleCSV() throws Exception {
        mockMvc.perform(get("/api/product/csv"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRatingPost() throws Exception {

        Vehicle vehicle = new Vehicle("Mercedes", "Class E",ItemConditions.USED,  2017,  45123, 1.9, 2000000);
        vehicleRepository.save(vehicle);
        Optional<Vehicle> savedVehicle = vehicleRepository.findById(vehicle.getId_vehicle());

        assertNotNull(savedVehicle);
    }

    @Test
    public void testSalonPercentageFill() throws Exception {
        String uri = "/api/fulfillment/1/fill";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testVehiclesInSalonGet() throws Exception {
        String uri = "/api/fulfillment/1/products";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }



}