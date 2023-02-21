package com.ecommerce.inditex.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Business object to encapsulate price filter data.
 */
@Data
@Builder
public class PriceFilterBO {

    /**
     * Brand id.
     */
    private String brandId;

    /**
     * Product id.
     */
    private String productId;

    /**
     * Applied date.
     */
    private LocalDateTime appliedDate;
}
