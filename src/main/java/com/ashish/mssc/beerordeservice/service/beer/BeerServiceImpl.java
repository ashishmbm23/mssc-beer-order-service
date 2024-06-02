package com.ashish.mssc.beerordeservice.service.beer;

import com.ashish.common.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BeerServiceImpl implements BeerService{

    public final static String BEER_PATH_V1 = "/api/v1/beer/";
    public final static String BEER_UPC_PATH_V1 = "/api/v1/beer/upc/";

    private final RestTemplate restTemplate;
    private String beerServiceHost;

    public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    public void setBeerServiceHost(@Value("${sfg.brewary.beer-service-host}") String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    @Override
    public Optional<BeerDto> getBeerById(String uuid) {
        return Optional.of( restTemplate.getForObject(
                beerServiceHost + BEER_PATH_V1 + uuid.toString(), BeerDto.class
        ));
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return Optional.of( restTemplate.getForObject(
                beerServiceHost + BEER_UPC_PATH_V1 + upc, BeerDto.class
        ));
    }
}
