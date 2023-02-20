package com.ecommerce.inditex.infrastructure.database.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
public class H2PriceEntity {

    @Id
    @Column(name = "PRICE_LIST")
    private Integer priceListId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private BigDecimal priceValue;

    @Column(name = "CURRENCY")
    private String priceCurrency;
}
