package com.mango.customer.exceptions;

import com.mango.customer.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final String MSG = "User %s not found";
    public UserNotFoundException(Long userId) {
        super(String.format(MSG, userId));
    }

	public UserNotFoundException(UserDto user) {
		super(String.format(MSG, user.getName()));
	}

}
