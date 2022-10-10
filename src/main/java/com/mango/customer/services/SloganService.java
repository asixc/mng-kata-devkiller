package com.mango.customer.services;


import com.mango.customer.dtos.SloganDto;

import java.util.List;

public interface SloganService {
    List<SloganDto> finalAll();

    SloganDto findById(final Long id);

    SloganDto create(final SloganDto SloganDto);

    SloganDto update(final SloganDto SloganDto);

    void deleteById(final Long id);
}
