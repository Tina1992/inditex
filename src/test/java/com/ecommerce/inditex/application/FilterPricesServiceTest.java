package com.ecommerce.inditex.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
import com.ecommerce.inditex.application.ports.PriceFilterStrategyPort;
import com.ecommerce.inditex.application.ports.PricesPort;
import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Unit tests for {@link FilterPricesService}.
 */
class FilterPricesServiceTest {

    public class TestContext {
        /**
         * Mocked {@link PricesPort}.
         */
        @Mock
        private PricesPort pricesProvider;

        /**
         * Mocked {@link PriceFilterStrategyPort}.
         */
        @Mock
        private PriceFilterStrategyPort priceFilterStrategyPort;

        /**
         * The {@link FilterPricesService} to test.
         */
        @InjectMocks
        private FilterPricesService filterPricesService;

        /**
         * Constructor.
         */
        TestContext() {
            initMocks(this);
        }
    }

    /**
     * Test context.
     */
    private TestContext testContext;

    /**
     * Test setup.
     */
    @BeforeEach
    public void setUp() {
        testContext = new TestContext();
    }

    /**
     * Test happy path for the {@link FilterPricesService}
     */
    @Test
    void testHappyPathFilterPrices() {
        String brandId = "brandId";
        String productId = "productId";
        LocalDateTime appliedTime = LocalDateTime.of(2023, 07, 07, 23, 00);
        PriceFilterBO priceFilterBO = PriceFilterBO.builder().brandId(brandId).productId(productId).appliedDate(appliedTime).build();
        PriceBO priceBO = PriceBO.builder().build();
        List<PriceBO> priceBOList = new ArrayList<>();

        when(testContext.pricesProvider.getPrices(priceFilterBO)).thenReturn(priceBOList);
        when(testContext.priceFilterStrategyPort.filter(priceBOList)).thenReturn(priceBO);

        PriceBO priceResponseValue = testContext.filterPricesService.getPrice(brandId, productId, appliedTime);

        assertEquals(priceBO, priceResponseValue);
        verify(testContext.pricesProvider).getPrices(priceFilterBO);
        verify(testContext.priceFilterStrategyPort).filter(priceBOList);
    }

    /**
     * Test no result found
     */
    @Test
    void testNoResultFound() {
        String brandId = "brandId";
        String productId = "productId";
        LocalDateTime appliedTime = LocalDateTime.of(2023, 07, 07, 23, 00);
        PriceFilterBO priceFilterBO = PriceFilterBO.builder().brandId(brandId).productId(productId).appliedDate(appliedTime).build();
        List<PriceBO> priceBOList = Collections.emptyList();

        when(testContext.pricesProvider.getPrices(priceFilterBO)).thenReturn(priceBOList);
        when(testContext.priceFilterStrategyPort.filter(priceBOList)).thenThrow(new NoResultFoundException());

        assertThrows(NoResultFoundException.class, () -> testContext.filterPricesService.getPrice(brandId, productId, appliedTime));

        verify(testContext.pricesProvider).getPrices(priceFilterBO);
        verify(testContext.priceFilterStrategyPort).filter(priceBOList);
    }
}