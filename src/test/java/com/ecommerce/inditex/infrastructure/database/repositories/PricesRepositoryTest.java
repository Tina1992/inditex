package com.ecommerce.inditex.infrastructure.database.repositories;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ecommerce.inditex.infrastructure.database.entities.PriceEntity;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Integration tests for the {@link PricesRepository} to check data.
 */
@DataJpaTest
class PricesRepositoryTest {

    /**
     * The {@link PricesRepository} to test.
     */
    @Autowired
    private PricesRepository pricesRepository;

    /**
     * Test to verify repository behaviour in test case #1:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-14T10:00</li>
     * </ul>
     */
    @Test
    void findApplicablePricesTestCaseOne() {
        List<PriceEntity> h2PriceEntities = pricesRepository.findApplicablePrices(1, Long.valueOf(35455), LocalDateTime.of(2020, Month.JUNE, 14, 10, 00));
        assertEquals(1, h2PriceEntities.size());
        assertEquals(1, h2PriceEntities.get(0).getPriceListId());
    }

    /**
     * Test to verify repository behaviour in test case #2:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-14T16:00</li>
     * </ul>
     */
    @Test
    void findApplicablePricesTestCaseTwo() {
        List<PriceEntity> h2PriceEntities = pricesRepository.findApplicablePrices(1, Long.valueOf(35455), LocalDateTime.of(2020, Month.JUNE, 14, 16, 00));
        assertEquals(2, h2PriceEntities.size());
        assertThat(h2PriceEntities, contains(
                hasProperty("priceListId", is(1)),
                hasProperty("priceListId", is(2))
        ));
    }

    /**
     * Test to verify repository behaviour in test case #3:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-14T21:00</li>
     * </ul>
     */
    @Test
    void findApplicablePricesTestCaseThree() {
        List<PriceEntity> h2PriceEntities = pricesRepository.findApplicablePrices( 1, Long.valueOf(35455), LocalDateTime.of(2020, Month.JUNE, 14, 21, 00));
        assertEquals(1, h2PriceEntities.size());
        assertEquals(1, h2PriceEntities.get(0).getPriceListId());
    }

    /**
     * Test to verify repository behaviour in test case #4:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-15T10:00</li>
     * </ul>
     */
    @Test
    void findApplicablePricesTestCaseFour() {
        List<PriceEntity> h2PriceEntities = pricesRepository.findApplicablePrices(1, Long.valueOf(35455), LocalDateTime.of(2020, Month.JUNE, 15, 10, 00));
        assertEquals(2, h2PriceEntities.size());
        assertThat(h2PriceEntities, contains(
                hasProperty("priceListId", is(1)),
                hasProperty("priceListId", is(3))
        ));
    }

    /**
     * Test to verify repository behaviour in test case #4:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-16T21:00</li>
     * </ul>
     */
    @Test
    void findApplicablePricesTestCaseFive() {
        List<PriceEntity> h2PriceEntities = pricesRepository.findApplicablePrices(1, Long.valueOf(35455), LocalDateTime.of(2020, Month.JUNE, 16, 21, 00));
        assertEquals(2, h2PriceEntities.size());
        assertThat(h2PriceEntities, contains(
                hasProperty("priceListId", is(1)),
                hasProperty("priceListId", is(4))
        ));
    }
}