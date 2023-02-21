package com.ecommerce.inditex.infrastructure.database.adapter;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.database.entities.BrandEntity;
import com.ecommerce.inditex.infrastructure.database.entities.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for the {@link PriceEntityAdapter}.
 */
class PriceEntityAdapterTest {

    /**
     * The {@link PriceEntityAdapter}.
     */
    private PriceEntityAdapter priceEntityAdapter;

    /**
     * Test setup.
     */
    @BeforeEach
    public void setUp() {
        priceEntityAdapter = Mappers.getMapper(PriceEntityAdapter.class);
    }

    /**
     * Test {@link PriceEntityAdapter#fromPriceEntity(PriceEntity)}
     * with null value.
     */
    @Test
    void fromH2PriceNullEntity() {
        PriceBO value = priceEntityAdapter.fromPriceEntity(null);

        assertNull(value);
    }

    /**
     * Test {@link PriceEntityAdapter#fromPriceEntity(PriceEntity)}
     * with {@link PriceEntity} fulfilled.
     */
    @Test
    void fromPriceEntity() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPriceListId(1);
        priceEntity.setPriceCurrency("EUR");
        priceEntity.setPriceValue(BigDecimal.TEN);
        priceEntity.setBrandEntity(brandEntity);
        priceEntity.setStartDate(LocalDateTime.now());
        priceEntity.setEndDate(LocalDateTime.now());
        priceEntity.setProductId(Long.valueOf(18515));

        PriceBO value = priceEntityAdapter.fromPriceEntity(priceEntity);

        assertEquals(priceEntity.getPriceListId().toString(), value.getPriceListId());
        assertEquals(priceEntity.getPriceValue(), value.getValue());
        assertEquals(priceEntity.getBrandEntity().getId().toString(), value.getBrandId());
        assertEquals(priceEntity.getEndDate(), value.getEndDate());
        assertEquals(priceEntity.getStartDate(), value.getStartDate());
        assertEquals(priceEntity.getPriceCurrency(), value.getCurrency());
        assertEquals(priceEntity.getProductId().toString(), value.getProductId());
    }
}