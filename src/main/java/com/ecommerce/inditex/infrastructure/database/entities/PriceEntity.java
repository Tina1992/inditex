package com.ecommerce.inditex.infrastructure.database.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Price entity class.
 */
@Entity
@Table(name = "PRICES")
@Data
public class PriceEntity {

    /**
     * Price list id.
     */
    @Id
    @Column(name = "PRICE_LIST")
    private Integer priceListId;

    /**
     * Product id.
     */
    @Column(name = "PRODUCT_ID")
    private Long productId;

    /**
     * Brand id.
     */
    @Column(name = "BRAND_ID")
    private Integer brandId;

    /**
     * Start date.
     */
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    /**
     * End date.
     */
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    /**
     * Priority.
     */
    @Column(name = "PRIORITY")
    private Integer priority;

    /**
     * Price value.
     */
    @Column(name = "PRICE")
    private BigDecimal priceValue;

    /**
     * Price currency.
     */
    @Column(name = "CURRENCY")
    private String priceCurrency;
}
