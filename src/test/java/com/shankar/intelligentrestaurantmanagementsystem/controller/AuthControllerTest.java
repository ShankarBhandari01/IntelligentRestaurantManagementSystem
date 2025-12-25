package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shankar.intelligentrestaurantmanagementsystem.AbstractIntegrationTest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AuthControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void login() throws Exception {
        var login = new LoginRequest("shankar@gmail.com", "12345");

        String loginResponse = mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(login))
                )
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.token").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = mapper.readTree(loginResponse).get("token").asText();
    }
}