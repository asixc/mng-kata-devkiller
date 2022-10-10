package com.mango.customer.services;

import com.mango.customer.dtos.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto create(final UserDto userDto);

    UserDto findById(final Long id);
	UserDto signing(final UserDto userDto);

    UserDto updateUser(final UserDto userDto);
	void delete(final Long userId);
}
