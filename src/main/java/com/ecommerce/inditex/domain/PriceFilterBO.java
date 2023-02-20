package com.ecommerce.inditex.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceFilterBO {

    private LocalDateTime appliedDate;

    private String productId;

    private String brandId;
}
