package com.mango.customer.controllers;

import com.mango.customer.dtos.SloganDto;
import com.mango.customer.services.SloganService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SloganController {

	private final SloganService sloganService;

	@GetMapping("/slogans")
	List<SloganDto> findlAll(){
		return this.sloganService.finalAll();
	}

	@PostMapping("/campaigns/slogan")
	SloganDto create(@Valid @RequestBody SloganDto sloganDto){
		return this.sloganService.create(sloganDto);
	}

	@PutMapping("/slogans")
	SloganDto update(@Valid @RequestBody SloganDto sloganDto){
		return this.sloganService.update(sloganDto);
	}

	@DeleteMapping("/Slogans/{id}")
	void deleteById(@PathVariable @NotNull final Long id){
		this.sloganService.deleteById(id);
	}
}
