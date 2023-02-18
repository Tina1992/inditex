package com.ecommerce.inditex.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PriceFilterEntity {

    private LocalDate appliedDate;

    private String productId;

    private String brandId;
}
