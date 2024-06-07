package com.ashish.common.model.events;

import com.ashish.common.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateOrderRequest {
    private final BeerOrderDto beerOrderDto;
}
