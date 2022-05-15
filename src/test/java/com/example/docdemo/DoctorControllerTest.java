package com.example.docdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("test doctors")
    public void testDoctors() throws Exception {

        String city = "Springfield";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors")
                        .param("city", city)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andReturn();
    }

    @Test
    @DisplayName("test cities")
    public void testCities() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors/cities"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andReturn();
    }

    @Test
    @DisplayName("test facilities")
    public void testFacilities() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors/facilities"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andReturn();
    }
}