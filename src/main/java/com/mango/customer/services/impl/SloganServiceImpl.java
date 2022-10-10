package com.mango.customer.services.impl;

import com.mango.customer.dtos.SloganDto;
import com.mango.customer.exceptions.SloganNotFoundException;
import com.mango.customer.exceptions.MaxNumberSlogans;
import com.mango.customer.exceptions.UserNotFoundException;
import com.mango.customer.mappers.SloganMapper;
import com.mango.customer.models.User;
import com.mango.customer.repository.SloganRepository;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.services.SloganService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SloganServiceImpl implements SloganService {

    private final SloganRepository sloganRepository;

    private final UserRepository userRepository;

    @Override
    public List<SloganDto> finalAll(){
        return this.sloganRepository.findAll().stream()
                .map(SloganMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public SloganDto findById(Long id){
        return this.sloganRepository.findById(id)
			.map(SloganMapper::to)
			.orElseThrow(SloganNotFoundException::new);
    }

    @Override
    public SloganDto create(SloganDto sloganDto){
        final Optional<User> optionalUser = this.userRepository.findById(sloganDto.getUser().getId());

        if (optionalUser.isPresent()) {
            if (optionalUser.get().getSlogans().size() < 3) {
                return SloganMapper.to(sloganRepository.save(SloganMapper.to(sloganDto)));
            }

        }

		throw new UserNotFoundException(sloganDto.getUser().getId());

    }

    @Override
    public SloganDto update(SloganDto sloganDto) {
        return SloganMapper.to(this.sloganRepository.save(SloganMapper.to(sloganDto)));
    }

    @Override
    public void deleteById(Long id){
        this.sloganRepository.deleteById(id);
    }
}
