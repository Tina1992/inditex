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
    private Integer brandId;

    /**
     * Product id.
     */
    private Long productId;

    /**
     * Applied date.
     */
    private LocalDateTime appliedDate;
}
