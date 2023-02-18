package com.ecommerce.inditex.strategies;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.exceptions.NoResultFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaxPriorityPriceFilterStrategyTest {

    private MaxPriorityPriceFilterStrategy maxPriorityPriceFilterStrategy;

    @BeforeEach
    public void setUp() {
        maxPriorityPriceFilterStrategy = new MaxPriorityPriceFilterStrategy();
    }

    @Test
    public void testFilterHappyPath() {
        PriceEntity highestPriority = PriceEntity.builder().priority(4).build();
        List<PriceEntity> priceEntities = Arrays.asList(PriceEntity.builder().priority(0).build(), highestPriority, PriceEntity.builder().priority(2).build());

        PriceEntity priceEntityValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceEntityValue);
    }

    @Test
    public void testFilterOneObject() {
        PriceEntity highestPriority = PriceEntity.builder().priority(4).build();
        List<PriceEntity> priceEntities = Arrays.asList(highestPriority);

        PriceEntity priceEntityValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceEntityValue);
    }

    @Test
    public void testFilterOneObjectWithNullValue() {
        PriceEntity highestPriority = PriceEntity.builder().priority(4).build();
        List<PriceEntity> priceEntities = Arrays.asList(PriceEntity.builder().build(), highestPriority, PriceEntity.builder().priority(2).build());

        PriceEntity priceEntityValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceEntityValue);
    }

    @Test
    public void testFilterWithNoResult() {
        List<PriceEntity> priceEntities = Collections.emptyList();

        assertThrows(NoResultFoundException.class, () -> maxPriorityPriceFilterStrategy.filter(priceEntities));
    }
}