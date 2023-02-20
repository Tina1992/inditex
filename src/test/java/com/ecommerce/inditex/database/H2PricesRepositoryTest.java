package com.ecommerce.inditex.database;

import com.ecommerce.inditex.infrastructure.database.entities.H2PriceEntity;
import com.ecommerce.inditex.infrastructure.database.repositories.H2PricesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class H2PricesRepositoryTest {

    @Autowired
    private H2PricesRepository h2PricesRepository;

    @Test
    void findApplicablePricesTwoValues() {
        List<H2PriceEntity> h2PriceEntities = h2PricesRepository.findApplicablePrices(Long.valueOf(35455), 1, LocalDateTime.of(2020, Month.JUNE, 14, 16, 00));
        assertEquals(2, h2PriceEntities.size());
        assertThat(h2PriceEntities, contains(
                hasProperty("priceListId", is(1)),
                hasProperty("priceListId", is(2))
        ));
    }

    @Test
    void findApplicablePricesOnlyOneValue() {
        List<H2PriceEntity> h2PriceEntities = h2PricesRepository.findApplicablePrices(Long.valueOf(35455), 1, LocalDateTime.of(2020, Month.JUNE, 14, 10, 00));
        assertEquals(1, h2PriceEntities.size());
        assertEquals(1, h2PriceEntities.get(0).getPriceListId());
    }

    @Test
    void findApplicablePricesNoValues() {
        List<H2PriceEntity> h2PriceEntities = h2PricesRepository.findApplicablePrices(Long.valueOf(35455), 1, LocalDateTime.of(2020, Month.JUNE, 14, 21, 00));
        assertEquals(1, h2PriceEntities.size());
        assertEquals(1, h2PriceEntities.get(0).getPriceListId());
    }

    @Test
    void findApplicablePricesTwoPrices() {
        List<H2PriceEntity> h2PriceEntities = h2PricesRepository.findApplicablePrices(Long.valueOf(35455), 1, LocalDateTime.of(2020, Month.JUNE, 15, 10, 00));
        assertEquals(2, h2PriceEntities.size());
        assertThat(h2PriceEntities, contains(
                hasProperty("priceListId", is(1)),
                hasProperty("priceListId", is(3))
        ));
    }

    @Test
    void findApplicablePricesTwoPrices2() {
        List<H2PriceEntity> h2PriceEntities = h2PricesRepository.findApplicablePrices(Long.valueOf(35455), 1, LocalDateTime.of(2020, Month.JUNE, 16, 21, 00));
        assertEquals(2, h2PriceEntities.size());
        assertThat(h2PriceEntities, contains(
                hasProperty("priceListId", is(1)),
                hasProperty("priceListId", is(4))
        ));
    }
}