package com.ecommerce.inditex.database;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.database.entities.H2PriceEntity;
import com.ecommerce.inditex.infrastructure.database.adapter.H2PriceEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class H2PriceBOMapperTest {

    private H2PriceEntityMapper h2PriceEntityMapper;

    @BeforeEach
    public void setUp()  {
        h2PriceEntityMapper = Mappers.getMapper(H2PriceEntityMapper.class);
    }

    @Test
    void fromH2PriceNullEntity() {
        PriceBO value = h2PriceEntityMapper.fromH2PriceEntity(null);

        assertNull(value);
    }

    @Test
    void fromH2PriceEntity() {
        H2PriceEntity h2PriceEntity = new H2PriceEntity();
        h2PriceEntity.setPriceListId(1);
        h2PriceEntity.setPriceCurrency("EUR");
        h2PriceEntity.setPriceValue(BigDecimal.TEN);
        h2PriceEntity.setBrandId(1);
        h2PriceEntity.setStartDate(LocalDateTime.now());
        h2PriceEntity.setEndDate(LocalDateTime.now());
        h2PriceEntity.setProductId(Long.valueOf(1515));

        PriceBO value = h2PriceEntityMapper.fromH2PriceEntity(h2PriceEntity);

        assertEquals(h2PriceEntity.getPriceListId().toString(), value.getPriceListId());
        assertEquals(h2PriceEntity.getPriceValue(), value.getPriceValue());
        assertEquals(h2PriceEntity.getBrandId().toString(), value.getBrandId());
        assertEquals(h2PriceEntity.getEndDate(), value.getEndDate());
        assertEquals(h2PriceEntity.getStartDate(), value.getStartDate());
        assertEquals(h2PriceEntity.getPriceCurrency(), value.getPriceCurrency());
        assertEquals(h2PriceEntity.getProductId().toString(), value.getProductId());
    }
}