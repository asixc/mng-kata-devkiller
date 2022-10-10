package com.mango.customer.mappers;

import com.mango.customer.dtos.UserDto;
import com.mango.customer.models.User;

import java.util.stream.Collectors;

public interface UserMapper {
    static UserDto to(User userEntity) {
        return UserDto.builder()
			.id(userEntity.getId())
			.name(userEntity.getName())
			.lastName(userEntity.getLastName())
			.address(userEntity.getAddress())
			.city(userEntity.getCity())
			.email(userEntity.getEmail())
			.slogans(userEntity.getSlogans().stream().map(SloganMapper::to).collect(Collectors.toList()))
			.build();
    }

    static User to(UserDto userDto) {
        return User.builder()
			.id(userDto.getId())
			.name(userDto.getName())
			.lastName(userDto.getLastName())
			.address(userDto.getAddress())
			.city(userDto.getCity())
			.email(userDto.getEmail())
			.slogans(userDto.getSlogans().stream().map(SloganMapper::to).collect(Collectors.toList()))
			.build();
    }
}
