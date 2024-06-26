package com.ashish.mssc.beerordeservice.web.mapper;

import com.ashish.common.model.BeerOrderDto;
import com.ashish.mssc.beerordeservice.domain.BeerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
