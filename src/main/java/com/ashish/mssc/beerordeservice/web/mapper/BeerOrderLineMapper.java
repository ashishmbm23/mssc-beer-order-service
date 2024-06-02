package com.ashish.mssc.beerordeservice.web.mapper;

import com.ashish.common.model.BeerOrderLineDto;
import com.ashish.mssc.beerordeservice.domain.BeerOrderLine;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper (uses = {DateMapper.class})
@DecoratedWith(BeerOrderLinerMapperDecorator.class)
public interface  BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
