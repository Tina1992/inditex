package com.ecommerce.inditex.services;

import com.ecommerce.inditex.application.FilterPricesService;
import com.ecommerce.inditex.application.ports.PricesPort;
import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
import com.ecommerce.inditex.infrastructure.rest.adapter.PriceBOMapper;
import com.ecommerce.inditex.application.ports.PriceFilterStrategyPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class FilterPricesServiceTest {

    public class TestContext {
        @Mock
        private PricesPort pricesProvider;

        @Mock
        private PriceFilterStrategyPort priceFilterStrategyPort;

        @InjectMocks
        private FilterPricesService filterPricesService;

        public TestContext() {
            initMocks(this);
        }
    }

    private TestContext testContext;

    @BeforeEach
    public void setUp() {
        testContext = new TestContext();
    }

    @Test
    void testHappyPathFilterPrices() {
        String brandId = "brandId";
        String productId = "productId";
        LocalDateTime appliedTime = LocalDateTime.of(2023,07, 07, 23, 00);
        PriceFilterBO priceFilterBO = PriceFilterBO.builder().brandId(brandId).productId(productId).appliedDate(appliedTime).build();
        PriceBO priceBO = PriceBO.builder().build();
        List<PriceBO> priceBOList = new ArrayList<>();

        when(testContext.pricesProvider.getPrices(priceFilterBO)).thenReturn(priceBOList);
        when(testContext.priceFilterStrategyPort.filter(priceBOList)).thenReturn(priceBO);

        PriceBO priceResponseValue = testContext.filterPricesService.filterPrices(brandId, productId, appliedTime);

        assertEquals(priceBO, priceResponseValue);
        verify(testContext.pricesProvider).getPrices(priceFilterBO);
        verify(testContext.priceFilterStrategyPort).filter(priceBOList);
    }

    @Test
    void testNoResultsProvided() {
        String brandId = "brandId";
        String productId = "productId";
        LocalDateTime appliedTime = LocalDateTime.of(2023,07, 07, 23, 00);
        PriceFilterBO priceFilterBO = PriceFilterBO.builder().brandId(brandId).productId(productId).appliedDate(appliedTime).build();

        when(testContext.pricesProvider.getPrices(priceFilterBO)).thenThrow(new NoResultFoundException());

        assertThrows(NoResultFoundException.class, () -> testContext.filterPricesService.filterPrices(brandId, productId, appliedTime));

        verify(testContext.pricesProvider).getPrices(priceFilterBO);
        verifyNoInteractions(testContext.priceFilterStrategyPort);
    }

    @Test
    void testNoFilterFoundProvided() {
        String brandId = "brandId";
        String productId = "productId";
        LocalDateTime appliedTime = LocalDateTime.of(2023,07, 07, 23, 00);
        PriceFilterBO priceFilterBO = PriceFilterBO.builder().brandId(brandId).productId(productId).appliedDate(appliedTime).build();
        List<PriceBO> priceBOList = Collections.emptyList();

        when(testContext.pricesProvider.getPrices(priceFilterBO)).thenReturn(priceBOList);
        when(testContext.priceFilterStrategyPort.filter(priceBOList)).thenThrow(new NoResultFoundException());

        assertThrows(NoResultFoundException.class, () -> testContext.filterPricesService.filterPrices(brandId, productId, appliedTime));

        verify(testContext.pricesProvider).getPrices(priceFilterBO);
        verify(testContext.priceFilterStrategyPort).filter(priceBOList);
    }
}