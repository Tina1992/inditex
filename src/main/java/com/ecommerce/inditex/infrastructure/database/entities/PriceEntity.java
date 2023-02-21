package com.ecommerce.inditex.infrastructure.database.entities;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

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
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    /**
     * Brand id.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    private BrandEntity brandEntity;

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
    @Column(name = "PRIORITY", nullable = false)
    @ColumnDefault("-10")
    private Integer priority;

    /**
     * Price value.
     */
    @Column(name = "PRICE", nullable = false)
    private BigDecimal priceValue;

    /**
     * Price currency.
     */
    @Column(name = "CURRENCY", nullable = false)
    private String priceCurrency;
}
