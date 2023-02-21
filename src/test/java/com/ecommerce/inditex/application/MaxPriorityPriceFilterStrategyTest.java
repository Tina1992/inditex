package com.ecommerce.inditex.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
import com.ecommerce.inditex.domain.PriceBO;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MaxPriorityPriceFilterStrategy}.
 */
class MaxPriorityPriceFilterStrategyTest {

    /**
     * The {@link MaxPriorityPriceFilterStrategy} to test.
     */
    private MaxPriorityPriceFilterStrategy maxPriorityPriceFilterStrategy;

    /**
     * Test setup.
     */
    @BeforeEach
    public void setUp() {
        maxPriorityPriceFilterStrategy = new MaxPriorityPriceFilterStrategy();
    }

    /**
     * Test happy path with several values to filter.
     */
    @Test
    public void testFilterHappyPath() {
        PriceBO highestPriority = PriceBO.builder().priority(4).build();
        List<PriceBO> priceEntities = Arrays.asList(PriceBO.builder().priority(0).build(), highestPriority, PriceBO.builder().priority(2).build());

        PriceBO priceBOValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceBOValue);
    }

    /**
     * Test happy path with only one value to filter.
     */
    @Test
    public void testFilterOneObject() {
        PriceBO highestPriority = PriceBO.builder().priority(4).build();
        List<PriceBO> priceEntities = Arrays.asList(highestPriority);

        PriceBO priceBOValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceBOValue);
    }

    /**
     * Test happy path with null among the values to filter.
     */
    @Test
    public void testFilterOneObjectWithNullValue() {
        PriceBO highestPriority = PriceBO.builder().priority(4).build();
        List<PriceBO> priceEntities = Arrays.asList(PriceBO.builder().build(), highestPriority, PriceBO.builder().priority(2).build());

        PriceBO priceBOValue = maxPriorityPriceFilterStrategy.filter(priceEntities);

        assertEquals(highestPriority, priceBOValue);
    }

    /**
     * Test error path.
     */
    @Test
    public void testFilterWithNoResult() {
        List<PriceBO> priceEntities = Collections.emptyList();

        assertThrows(NoResultFoundException.class, () -> maxPriorityPriceFilterStrategy.filter(priceEntities));
    }
}