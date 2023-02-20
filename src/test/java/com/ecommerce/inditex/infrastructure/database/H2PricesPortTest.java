package com.ecommerce.inditex.infrastructure.database;

import com.ecommerce.inditex.infrastructure.database.adapter.H2PriceEntityMapper;
import com.ecommerce.inditex.infrastructure.database.H2PricesPort;
import com.ecommerce.inditex.infrastructure.database.repositories.H2PricesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

class H2PricesPortTest {

    private TestContext testContext;

    public class TestContext {

        @Mock
        private H2PricesRepository h2PricesRepository;

        @Mock
        private H2PriceEntityMapper h2PriceEntityMapper;

        @InjectMocks
        private H2PricesPort h2PricesProviderService;

        public TestContext() {
            initMocks(this);
        }

    }

    @BeforeEach
    public void setUp() {
        testContext = new TestContext();
    }

    @Test
    void getPrices() {


    }
}