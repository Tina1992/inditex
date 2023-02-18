package com.ecommerce.inditex.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class H2PricesProviderServiceTest {

    private TestContext testContext;

    public class TestContext {

        @Mock
        private H2PricesRepository h2PricesRepository;

        @Mock
        private H2PriceEntityMapper h2PriceEntityMapper;

        @InjectMocks
        private H2PricesProviderService h2PricesProviderService;

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