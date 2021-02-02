package com.lab9.controller;

import com.lab9.model.Carshowroom;
import com.lab9.repository.CarshowroomRepository;
import com.lab9.service.CarShowroomService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@RunWith(Parameterized.class)
class CarShowroomControllerTest {

    @Mock
    private CarshowroomRepository carshowroomRepository;

    @Mock
    private CarShowroomService carShowroomService;

    @InjectMocks
    private CarShowroomController carShowroomController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.print("Hey there");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carShowroomController).build();
    }

    @Test
    public void testCarshowroomPost() throws Exception {
        Carshowroom carshowroom = new Carshowroom(111, "testsalon", 99);
        carshowroomRepository.save(carshowroom);
        Optional<Carshowroom> savedCarshowroom = carshowroomRepository.findById(111L);

        assertNotNull(savedCarshowroom);
    }

    @Test
    public void testCarshowroomGet() throws Exception {
        mockMvc.perform(get("/api/fulfillment"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    @Test
    public void testCarShowroomDelete() throws Exception {
        String uri = "/api/fulfillment/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }
}