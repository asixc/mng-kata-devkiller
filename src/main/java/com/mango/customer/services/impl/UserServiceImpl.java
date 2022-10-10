package com.mango.customer.services.impl;

import com.mango.customer.dtos.UserDto;
import com.mango.customer.exceptions.UserNotFoundException;
import com.mango.customer.mappers.UserMapper;
import com.mango.customer.models.User;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

	private static final BiFunction<UserDto, User, User> updateUser = (userUpdated, userDb) ->  User.builder()
		.name(userUpdated.getName())
		.lastName(userUpdated.getLastName())
		.address(userUpdated.getAddress())
		.city(userUpdated.getCity())
		.email(userUpdated.getEmail())
		.build();

    @Override
    public List<UserDto> findAll() {
        return this.repository.findAll().stream().map(UserMapper::to).collect(Collectors.toList());
    }

    @Override
    public UserDto create(final UserDto userDto) {
        return UserMapper.to(this.repository.save(UserMapper.to(userDto)));
    }

    @Override
    public UserDto findById(final Long id) {
        return this.repository.findById(id)
                .map(UserMapper::to)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

	@Override
	public UserDto signing(UserDto userDto) {
		final var user =  this.repository.findByNameAndLastNameAndAddressAndCityAndEmail(
			userDto.getName(),
			userDto.getLastName(),
			userDto.getAddress(),
			userDto.getCity(),
			userDto.getEmail()
		);

		if (user.isPresent()) {
			new UserNotFoundException(userDto);
		}
		return UserMapper.to(user.get());
	}

	@Override
    public UserDto updateUser(UserDto userDto) {
        return this.repository.findById(userDto.getId())
                .map((userDB)->updateUser.apply(userDto, userDB))
                .map(UserMapper::to)
                .orElseThrow(() -> new UserNotFoundException(userDto.getId()));
    }

	@Override
	public void delete(Long userId) {
		this.repository.deleteById(userId);
	}

}
