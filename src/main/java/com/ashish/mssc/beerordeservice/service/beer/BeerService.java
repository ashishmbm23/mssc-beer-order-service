package com.ashish.mssc.beerordeservice.service.beer;

import com.ashish.mssc.beerordeservice.web.model.BeerDto;

import java.util.Optional;

public interface BeerService {

    Optional<BeerDto> getBeerById(String uuid);
    Optional<BeerDto> getBeerByUpc(String upc);
}
