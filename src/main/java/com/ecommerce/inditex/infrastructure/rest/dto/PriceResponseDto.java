package com.ecommerce.inditex.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PriceResponseDto {

    private String priceListId;

    private String productId;

    private String brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal priceValue;

    private String priceCurrency;
}
