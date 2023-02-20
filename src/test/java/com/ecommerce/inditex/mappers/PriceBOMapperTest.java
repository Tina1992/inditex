package com.ecommerce.inditex.mappers;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import com.ecommerce.inditex.infrastructure.rest.adapter.PriceBOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriceBOMapperTest {

    private PriceBOMapper priceBOMapper;

    @BeforeEach
    public void setUp() {
        priceBOMapper = Mappers.getMapper(PriceBOMapper.class);
    }

    @Test
    void testFromPriceNullEntity() {
        PriceResponseDto value = priceBOMapper.fromPriceBO(null);

        assertNull(value);
    }

    @Test
    void testFromFullPriceEntity() {
        PriceBO priceBO = PriceBO.builder()
                .priceListId("priceListId")
                .productId("productId")
                .brandId("brandId")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .priceValue(BigDecimal.TEN)
                .priceCurrency("EUR")
                .build();

        PriceResponseDto value = priceBOMapper.fromPriceBO(priceBO);

        assertEquals(priceBO.getPriceListId(), value.getPriceListId());
        assertEquals(priceBO.getProductId(), value.getProductId());
        assertEquals(priceBO.getBrandId(), value.getBrandId());
        assertEquals(priceBO.getStartDate(), value.getStartDate());
        assertEquals(priceBO.getEndDate(), value.getEndDate());
        assertEquals(priceBO.getPriceValue(), value.getPriceValue());
        assertEquals(priceBO.getPriceCurrency(), value.getPriceCurrency());
    }
}