package com.ashish.mssc.beerordeservice.web.mapper;

import com.ashish.mssc.beerordeservice.domain.BeerOrder;
import com.ashish.mssc.beerordeservice.web.model.BeerOrderDto;
import org.mapstruct.Mapper;

@Mapper( uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
