package com.ecommerce.inditex.infrastructure.database.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Brand entity class.
 */
@Entity
@Table(name = "PRICES")
@Data
public class BrandEntity {

    /**
     * Brand id.
     */
    @Id
    @Column(name = "BRAND_ID")
    private Integer id;


    /**
     * Brand name.
     */
    @Column(name = "BRAND_NAME")
    private Integer name;

    /**
     * Price entity list.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "brandEntity")
    private List<PriceEntity> priceEntityList;
}
