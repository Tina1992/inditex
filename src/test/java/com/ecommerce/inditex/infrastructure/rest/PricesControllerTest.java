package com.ecommerce.inditex.infrastructure.rest;

import com.ecommerce.inditex.application.FilterPricesService;
import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import com.ecommerce.inditex.infrastructure.rest.adapter.PriceResponseAdapter;
import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Unit test for {@link PricesController}.
 */
class PricesControllerTest {

    public class TestContext {
        /**
         * The mocked {@link FilterPricesService}
         */
        @Mock
        private FilterPricesService filterPricesService;

        /**
         * The mocked {@link PriceResponseAdapter}
         */
        @Mock
        private PriceResponseAdapter priceResponseAdapter;

        @InjectMocks
        private PricesController pricesController;

        /**
         * Constructor
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
     * Test setup
     */
    @BeforeEach
    public void setUp() {
        testContext = new TestContext();
    }

    /**
     * Test happy path of controller
     */
    @Test
    void testHappyPathGetPrice() {
        final Integer brandId = 1;
        final Long productId = 35455L;
        final LocalDateTime appliedTime = LocalDateTime.of(2023, 07, 07, 23, 00);
        final PriceBO priceBO = PriceBO.builder().build();
        final PriceResponseDto priceResponseDto = PriceResponseDto.builder().build();

        when(testContext.filterPricesService.getPrice(brandId, productId, appliedTime)).thenReturn(priceBO);
        when(testContext.priceResponseAdapter.fromPriceBO(priceBO)).thenReturn(priceResponseDto);

        PriceResponseDto value = testContext.pricesController.getPrice(brandId, productId, appliedTime);

        assertEquals(priceResponseDto, value);
        verify(testContext.filterPricesService).getPrice(brandId, productId, appliedTime);
        verify(testContext.priceResponseAdapter).fromPriceBO(priceBO);
    }
}