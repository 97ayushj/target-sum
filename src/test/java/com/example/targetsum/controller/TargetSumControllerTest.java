package com.example.targetsum.controller;

import com.example.targetsum.exception.BadRequestException;
import com.example.targetsum.exception.NotFoundException;
import com.example.targetsum.model.RequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TargetSumControllerTest {

    @InjectMocks
    private TargetSumController targetSumController;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Happy Scenario")
    void findPairTest() throws Exception {
        mockMvc.perform(post("/sum/target")
                        .content(asJsonString(getMockReq())) // Convert the RequestModel to JSON
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'result':[0,1]}"));
    }

    @Test
    @DisplayName("Test BadRequestException")
    void findPairTestBadRequestException() throws Exception {

        RequestModel req = new RequestModel();
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        req.setTarget(9);
        req.setNumbers(numbers);

        mockMvc.perform(post("/sum/target").content(asJsonString(req)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException)).andExpect(result -> assertEquals("Array of numbers is not valid", result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("Test NotFoundException")
    void findPairTestNotFoundException() throws Exception {
        RequestModel req = new RequestModel();
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(11);
        numbers.add(15);
        req.setTarget(9);
        req.setNumbers(numbers);

        mockMvc.perform(post("/sum/target").content(asJsonString(req)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals("Target sum is not posible using input numbers",result.getResolvedException().getMessage()));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RequestModel getMockReq() {/**/
        RequestModel req = new RequestModel();
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(6);
        numbers.add(11);
        numbers.add(15);
        req.setTarget(9);
        req.setNumbers(numbers);
        return req;
    }
}