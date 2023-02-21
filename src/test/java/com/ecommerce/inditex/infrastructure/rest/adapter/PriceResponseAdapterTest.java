package com.ecommerce.inditex.infrastructure.rest.adapter;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for {@link PriceResponseAdapter}.
 */
class PriceResponseAdapterTest {

    /**
     * The {@link PriceResponseAdapter} to test.
     */
    private PriceResponseAdapter priceResponseAdapter;

    /**
     * Test setup.
     */
    @BeforeEach
    public void setUp() {
        priceResponseAdapter = Mappers.getMapper(PriceResponseAdapter.class);
    }

    /**
     * Test {@link PriceResponseAdapter#fromPriceBO(PriceBO)}
     * with null value.
     */
    @Test
    void testFromPriceNullEntity() {
        PriceResponseDto value = priceResponseAdapter.fromPriceBO(null);

        assertNull(value);
    }

    /**
     * Test {@link PriceResponseAdapter#fromPriceBO(PriceBO)}
     * with {@link PriceBO} value.
     */
    @Test
    void testFromFullPriceEntity() {
        PriceBO priceBO = PriceBO.builder()
                .priceListId("priceListId")
                .productId("productId")
                .brandId("brandId")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .value(BigDecimal.TEN)
                .currency("EUR")
                .build();

        PriceResponseDto value = priceResponseAdapter.fromPriceBO(priceBO);

        assertEquals(priceBO.getPriceListId(), value.getPriceListId());
        assertEquals(priceBO.getProductId(), value.getProductId());
        assertEquals(priceBO.getBrandId(), value.getBrandId());
        assertEquals(priceBO.getStartDate(), value.getStartDate());
        assertEquals(priceBO.getEndDate(), value.getEndDate());
        assertEquals(priceBO.getValue(), value.getValue());
        assertEquals(priceBO.getCurrency(), value.getCurrency());
    }
}