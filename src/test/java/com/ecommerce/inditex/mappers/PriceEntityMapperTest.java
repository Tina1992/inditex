package com.ecommerce.inditex.mappers;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.entities.PriceResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriceEntityMapperTest {

    private PriceEntityMapper priceEntityMapper;

    @BeforeEach
    public void setUp() {
        priceEntityMapper = Mappers.getMapper(PriceEntityMapper.class);
    }

    @Test
    void testFromPriceNullEntity() {
        PriceResponseEntity value = priceEntityMapper.fromPriceEntity(null);

        assertNull(value);
    }

    @Test
    void testFromFullPriceEntity() {
        PriceEntity priceEntity = PriceEntity.builder()
                .priceListId("priceListId")
                .productId("productId")
                .brandId("brandId")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .priceValue(BigDecimal.TEN)
                .priceCurrency("EUR")
                .build();

        PriceResponseEntity value = priceEntityMapper.fromPriceEntity(priceEntity);

        assertEquals(priceEntity.getPriceListId(), value.getPriceListId());
        assertEquals(priceEntity.getProductId(), value.getProductId());
        assertEquals(priceEntity.getBrandId(), value.getBrandId());
        assertEquals(priceEntity.getStartDate(), value.getStartDate());
        assertEquals(priceEntity.getEndDate(), value.getEndDate());
        assertEquals(priceEntity.getPriceValue(), value.getPriceValue());
        assertEquals(priceEntity.getPriceCurrency(), value.getPriceCurrency());
    }
}