package com.ecommerce.inditex.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceFilterEntity {

    private LocalDateTime appliedDate;

    private String productId;

    private String brandId;
}
