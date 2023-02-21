package com.ecommerce.inditex.infrastructure.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import com.ecommerce.inditex.infrastructure.database.adapter.PriceEntityAdapter;
import com.ecommerce.inditex.infrastructure.database.entities.PriceEntity;
import com.ecommerce.inditex.infrastructure.database.repositories.PricesRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Unit tests for the {@link PricesPortImpl}.
 */
class PricesPortImplTest {

    /**
     * Test context.
     */
    private TestContext testContext;

    public class TestContext {

        /**
         * Mocked {@link PricesRepository}.
         */
        @Mock
        private PricesRepository pricesRepository;

        /**
         * Mocked {@link PriceEntityAdapter}.
         */
        @Mock
        private PriceEntityAdapter priceEntityAdapter;

        /**
         * The {@link PricesPortImpl} to test.
         */
        @InjectMocks
        private PricesPortImpl pricesPortImpl;

        /**
         * Constructor.
         */
        TestContext() {
            initMocks(this);
        }

    }

    /**
     * Test setup.
     */
    @BeforeEach
    public void setUp() {
        testContext = new TestContext();
    }

    /**
     * Test happy path for {@link PricesPortImpl#getPrices(PriceFilterBO)}
     */
    @Test
    void testHappyPathGetPrices() {
        Integer brandId = 1;
        Long productId = Long.valueOf(1);
        LocalDateTime appliedTime = LocalDateTime.of(2023, 07, 07, 23, 00);
        PriceFilterBO priceFilterBO = PriceFilterBO.builder().brandId(brandId).productId(productId).appliedDate(appliedTime).build();
        PriceEntity priceEntity = new PriceEntity();
        List<PriceEntity> databaseResults = Arrays.asList(priceEntity);
        PriceBO priceBO = PriceBO.builder().build();

        when(testContext.pricesRepository.findApplicablePrices(brandId, productId, appliedTime)).thenReturn(databaseResults);
        when(testContext.priceEntityAdapter.fromPriceEntity(priceEntity)).thenReturn(priceBO);

        List<PriceBO> values = testContext.pricesPortImpl.getPrices(priceFilterBO);

        assertEquals(1, values.size());
        assertEquals(priceBO, values.get(0));
        verify(testContext.pricesRepository).findApplicablePrices(brandId, productId, appliedTime);
        verify(testContext.priceEntityAdapter).fromPriceEntity(priceEntity);
    }

    /**
     * Test error path for {@link PricesPortImpl#getPrices(PriceFilterBO)}
     * when getting prices from database.
     */
    @Test
    void testErrorPathWhenGettingPricesFromDatabase() {
        Integer brandId = 1;
        Long productId = Long.valueOf(1);
        LocalDateTime appliedTime = LocalDateTime.of(2023, 07, 07, 23, 00);
        PriceFilterBO priceFilterBO = PriceFilterBO.builder().brandId(brandId).productId(productId).appliedDate(appliedTime).build();

        when(testContext.pricesRepository.findApplicablePrices(brandId, productId, appliedTime)).thenThrow(new NoResultFoundException());

        assertThrows(NoResultFoundException.class, () -> testContext.pricesPortImpl.getPrices(priceFilterBO));

        verify(testContext.pricesRepository).findApplicablePrices(brandId, productId, appliedTime);
        verifyNoInteractions(testContext.priceEntityAdapter);
    }
}