package com.mango.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mango.customer.controllers.UserController;
import com.mango.customer.exceptions.UserNotFoundException;
import com.mango.customer.services.UserService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userServiceMock;

    @Test
    void getUserById() throws Exception {
        final String JSONResponse = "{\"timestamp\":\"2022-09-14T16:04:18.9649337\",\"status\":404,\"method\":\"GET\",\"message\":null,\"path\":\"getUser\"}";
        when(userServiceMock.findById(2L)).thenThrow(new UserNotFoundException(2L));

        mockMvc.perform(get("/users/{id}", 2L)
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.is(404)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("User 2 not found")));

    }
}
