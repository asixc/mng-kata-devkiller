package com.mango.customer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SloganDto {

    private Long id;

    @NotBlank
    private String slogan;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull
    private UserDto user;

}
