package com.ecommerce.inditex.strategies;

import com.ecommerce.inditex.application.MaxPriorityPriceFilterStrategy;
import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
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
        PriceBO highestPriority = PriceBO.builder().priority(4).build();
        List<PriceBO> priceEntities = Arrays.asList(PriceBO.builder().priority(0).build(), highestPriority, PriceBO.builder().priority(2).build());

        PriceBO priceBOValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceBOValue);
    }

    @Test
    public void testFilterOneObject() {
        PriceBO highestPriority = PriceBO.builder().priority(4).build();
        List<PriceBO> priceEntities = Arrays.asList(highestPriority);

        PriceBO priceBOValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceBOValue);
    }

    @Test
    public void testFilterOneObjectWithNullValue() {
        PriceBO highestPriority = PriceBO.builder().priority(4).build();
        List<PriceBO> priceEntities = Arrays.asList(PriceBO.builder().build(), highestPriority, PriceBO.builder().priority(2).build());

        PriceBO priceBOValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceBOValue);
    }

    @Test
    public void testFilterWithNoResult() {
        List<PriceBO> priceEntities = Collections.emptyList();

        assertThrows(NoResultFoundException.class, () -> maxPriorityPriceFilterStrategy.filter(priceEntities));
    }
}