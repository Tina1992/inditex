package com.ecommerce.inditex.services;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.entities.PriceFilterEntity;
import com.ecommerce.inditex.entities.PriceResponseEntity;
import com.ecommerce.inditex.exceptions.NoResultFoundException;
import com.ecommerce.inditex.mappers.PriceEntityMapper;
import com.ecommerce.inditex.strategies.PriceFilterStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class FilterPricesServiceTest {

    public class TestContext {
        @Mock
        private PricesProviderService pricesProvider;

        @Mock
        private PriceFilterStrategy priceFilterStrategy;

        @Mock
        private PriceEntityMapper priceEntityMapper;

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
        PriceFilterEntity priceFilterEntity = PriceFilterEntity.builder().build();
        PriceEntity priceEntity = PriceEntity.builder().build();
        PriceResponseEntity priceResponseEntity = PriceResponseEntity.builder().build();
        List<PriceEntity> priceEntityList = new ArrayList<>();

        when(testContext.pricesProvider.getPrices(priceFilterEntity)).thenReturn(priceEntityList);
        when(testContext.priceFilterStrategy.filter(priceEntityList)).thenReturn(priceEntity);
        when(testContext.priceEntityMapper.fromPriceEntity(priceEntity)).thenReturn(priceResponseEntity);

        PriceResponseEntity priceResponseValue = testContext.filterPricesService.filterPrices(priceFilterEntity);

        assertEquals(priceResponseEntity, priceResponseValue);
        verify(testContext.pricesProvider).getPrices(priceFilterEntity);
        verify(testContext.priceFilterStrategy).filter(priceEntityList);
        verify(testContext.priceEntityMapper).fromPriceEntity(priceEntity);
    }

    @Test
    void testNoResultsProvided() throws NoResultFoundException {
        PriceFilterEntity priceFilterEntity = PriceFilterEntity.builder().build();

        when(testContext.pricesProvider.getPrices(priceFilterEntity)).thenThrow(new NoResultFoundException());

        assertThrows(NoResultFoundException.class, () -> testContext.filterPricesService.filterPrices(priceFilterEntity));

        verify(testContext.pricesProvider).getPrices(priceFilterEntity);
        verifyNoInteractions(testContext.priceFilterStrategy);
        verifyNoInteractions(testContext.priceEntityMapper);
    }

    @Test
    void testNoFilterFoundProvided() throws NoResultFoundException {
        PriceFilterEntity priceFilterEntity = PriceFilterEntity.builder().build();
        List<PriceEntity> priceEntityList = Collections.emptyList();

        when(testContext.pricesProvider.getPrices(priceFilterEntity)).thenReturn(priceEntityList);
        when(testContext.priceFilterStrategy.filter(priceEntityList)).thenThrow(new NoResultFoundException());

        assertThrows(NoResultFoundException.class, () -> testContext.filterPricesService.filterPrices(priceFilterEntity));

        verify(testContext.pricesProvider).getPrices(priceFilterEntity);
        verify(testContext.priceFilterStrategy).filter(priceEntityList);
        verifyNoInteractions(testContext.priceEntityMapper);
    }
}