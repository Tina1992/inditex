package com.ecommerce.inditex.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PriceResponseEntity {

    private String priceListId;

    private String productId;

    private String brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal priceValue;

    private String priceCurrency;
}
