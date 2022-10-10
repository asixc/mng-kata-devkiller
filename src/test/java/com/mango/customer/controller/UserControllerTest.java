package com.mango.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mango.customer.controllers.UserController;
import com.mango.customer.dtos.UserDto;
import com.mango.customer.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userServiceMock;

    @Test
    void getUsers() throws Exception {
        final UserDto dtoResponse = getUserDto();
        final String JSONResponse = objectMapper.writeValueAsString(List.of(dtoResponse));
        when(userServiceMock.findAll()).thenReturn(List.of(dtoResponse));

        mockMvc.perform(get("/users")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(JSONResponse));

    }

    @Test
    void getUserById() throws Exception {
        final UserDto dtoResponse = getUserDto();
        final String JSONResponse = objectMapper.writeValueAsString(dtoResponse);
        when(userServiceMock.findById(2L)).thenReturn(dtoResponse);

        mockMvc.perform(get("/users/{id}", 2L)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(JSONResponse));

    }

/*
    @Test
    void createUserTest() throws Exception {
        // ToDo Finish when save a user - integration controller
        final UserDto dtoResponse = getUserDto();
        final String JSONResponse = objectMapper.writeValueAsString(dtoResponse);
        when(userServiceMock.create(any())).thenReturn(dtoResponse);

        mockMvc.perform(post("/users")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(content().string(JSONResponse));

    }
*/
    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform( delete("/users/{id}", 1)
			.contentType("application/json"))
			.andExpect(status().isAccepted());
    }
    private UserDto getUserDto() {
        final var userDto = UserDto.builder().id(1L).build();
        userDto.setName("Jhon");
        userDto.setLastName("Doe");

        return userDto;
    }
}
