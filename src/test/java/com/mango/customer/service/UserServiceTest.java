package com.mango.customer.service;

import com.mango.customer.dtos.UserDto;
import com.mango.customer.exceptions.UserNotFoundException;
import com.mango.customer.models.User;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.services.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
	UserRepository userRepositoryMock;

    @InjectMocks
	UserServiceImpl sut;

    @Test
    @DisplayName("Test findAll")
    void testFindAll(){
        when(userRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        final var res = this.sut.findAll();

        assertEquals(Collections.emptyList(), res);
        verify(userRepositoryMock).findAll();
    }

    @Test
    @DisplayName("Test create")
    void testCreateUser(){
        final var userDto = new UserDto();
        final var userEntity =  new User();
        when(userRepositoryMock.save(any())).thenReturn(userEntity);

        final var res = this.sut.create(userDto);

        assertEquals(userDto, res);
        verify(userRepositoryMock).save(any());
    }

    @Test
    @DisplayName("Test FindById")
    void findById() throws UserNotFoundException {

        when(userRepositoryMock.findById(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () ->
            this.sut.findById(2L)
        );

        String expectedMessage = "User 2 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryMock).findById(any());
    }

}
