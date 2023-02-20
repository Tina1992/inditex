package com.ecommerce.inditex.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity to encapsulate price data
 */
@Data
@Builder
public class PriceBO {

    private String priceListId;

    private String productId;

    private String brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priority;

    private BigDecimal priceValue;

    private String priceCurrency;
}
