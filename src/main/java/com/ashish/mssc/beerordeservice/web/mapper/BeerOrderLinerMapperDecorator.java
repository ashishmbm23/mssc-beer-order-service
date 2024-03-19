package com.ashish.mssc.beerordeservice.web.mapper;

import com.ashish.mssc.beerordeservice.domain.BeerOrderLine;
import com.ashish.mssc.beerordeservice.service.beer.BeerService;
import com.ashish.mssc.beerordeservice.web.model.BeerDto;
import com.ashish.mssc.beerordeservice.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class BeerOrderLinerMapperDecorator implements BeerOrderLineMapper {

    private BeerOrderLineMapper beerOrderLineMapper;
    private BeerService beerService;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(line.getUpc());

        beerDtoOptional.ifPresent(beerDto -> {
            orderLineDto.setBeerName(beerDto.getBeerName());
            orderLineDto.setBeerStyle(beerDto.getBeerStyle());
            orderLineDto.setPrice(beerDto.getPrice());
            orderLineDto.setBeerId(beerDto.getId());
        });

        return orderLineDto;
    }

}
