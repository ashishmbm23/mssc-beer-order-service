package com.ashish.mssc.beerordeservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BeerOrderLine extends BaseEntity{

    @Builder
    public BeerOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         BeerOrder beerOrder, UUID beerId, Integer orderQuantity,
                         Integer quantityAllocated, String upc) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerOrder = beerOrder;
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
        this.quantityAllocated = quantityAllocated;
        this.upc = upc;
    }

    @ManyToOne
    private BeerOrder beerOrder;
    private UUID beerId;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
    private String upc;
}
