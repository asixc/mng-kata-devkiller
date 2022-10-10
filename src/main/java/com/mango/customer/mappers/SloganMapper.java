package com.mango.customer.mappers;

import com.mango.customer.dtos.SloganDto;
import com.mango.customer.models.Slogan;

public interface SloganMapper {
    static SloganDto to(Slogan slogan) {
        return SloganDto.builder()
			.id(slogan.getId())
			.slogan(slogan.getSlogan())
			.createdAt(slogan.getCreatedAt())
			.updatedAt(slogan.getUpdatedAt())
			.build();
    }

    static Slogan to(SloganDto sloganDto) {
        return Slogan.builder()
			.id(sloganDto.getId())
			.slogan(sloganDto.getSlogan())
			.user(UserMapper.to(sloganDto.getUser()))
			.createdAt(sloganDto.getCreatedAt())
			.updatedAt(sloganDto.getUpdatedAt())
			.build();
    }
}
