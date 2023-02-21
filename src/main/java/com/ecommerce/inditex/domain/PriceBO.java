package com.ecommerce.inditex.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Business object to encapsulate price data.
 */
@Data
@Builder
public class PriceBO {

    /**
     * Price list id.
     */
    private String priceListId;

    /**
     * Product id.
     */
    private String productId;

    /**
     * Brand id.
     */
    private String brandId;

    /**
     * Start date.
     */
    private LocalDateTime startDate;

    /**
     * End date.
     */
    private LocalDateTime endDate;

    /**
     * Priority.
     */
    private Integer priority;

    /**
     * Value.
     */
    private BigDecimal value;

    /**
     * Currency.
     */
    private String currency;
}
