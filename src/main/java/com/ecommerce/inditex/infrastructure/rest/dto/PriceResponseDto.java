package com.ecommerce.inditex.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Price response data transfer object.
 */
@Data
@Builder
public class PriceResponseDto {

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
     * Value.
     */
    private BigDecimal value;

    /**
     * Currency.
     */
    private String currency;
}
