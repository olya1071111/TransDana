package com.academy.TransDana.controllers;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Data
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void LoginSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("username", "user")
                        .param("password", "111"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users/routes"));

    }

    @Test
    public void LoginFailTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("username", "user")
                        .param("password", "222"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?error"));

    }
}
