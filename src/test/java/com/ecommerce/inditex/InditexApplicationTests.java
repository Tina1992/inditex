package com.ecommerce.inditex;

import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Application tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InditexApplicationTests {

    /**
     * Port
     */
    @Value(value = "${local.server.port}")
    private int port;

    /**
     * Rest template
     */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test url
     */
    private String testUrl;

    @BeforeEach
    public void setUp() {
        testUrl = String.format("http://localhost:%s/brands/1/products/35455?applied_time=", port);
    }


    /**
     * Test to verify response in test case #1:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-14T10:00</li>
     * </ul>
     */
    @Test
    void testCaseOne() {
        PriceResponseDto priceResponseDto = this.restTemplate.getForObject(testUrl + "2020-06-14T10:00", PriceResponseDto.class);
        assertEquals("1", priceResponseDto.getPriceListId());
        assertEquals("1", priceResponseDto.getBrandId());
        assertEquals(LocalDateTime.of(2020,06,14,00,00), priceResponseDto.getStartDate());
        assertEquals(LocalDateTime.of(2020,12,31,23,59,59), priceResponseDto.getEndDate());
        assertEquals("35455", priceResponseDto.getProductId());
        assertEquals("EUR", priceResponseDto.getCurrency());
        assertThat(priceResponseDto.getValue(), Matchers.comparesEqualTo(BigDecimal.valueOf(35.50)));
    }

    /**
     * Test to verify response in test case #2:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-14T16:00</li>
     * </ul>
     */
    @Test
    void testCaseTwo() {
        PriceResponseDto priceResponseDto = this.restTemplate.getForObject(testUrl + "2020-06-14T16:00", PriceResponseDto.class);
        assertEquals("2", priceResponseDto.getPriceListId());
        assertEquals("1", priceResponseDto.getBrandId());
        assertEquals(LocalDateTime.of(2020,06,14,15,00), priceResponseDto.getStartDate());
        assertEquals(LocalDateTime.of(2020,06,14,18,30), priceResponseDto.getEndDate());
        assertEquals("35455", priceResponseDto.getProductId());
        assertEquals("EUR", priceResponseDto.getCurrency());
        assertThat(priceResponseDto.getValue(), Matchers.comparesEqualTo(BigDecimal.valueOf(25.45)));
    }

    /**
     * Test to verify response in test case #3:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-14T21:00</li>
     * </ul>
     */
    @Test
    void testCaseThree() {
        PriceResponseDto priceResponseDto = this.restTemplate.getForObject(testUrl + "2020-06-14T21:00", PriceResponseDto.class);
        assertEquals("1", priceResponseDto.getPriceListId());
        assertEquals("1", priceResponseDto.getBrandId());
        assertEquals(LocalDateTime.of(2020,06,14,00,00), priceResponseDto.getStartDate());
        assertEquals(LocalDateTime.of(2020,12,31,23,59,59), priceResponseDto.getEndDate());
        assertEquals("35455", priceResponseDto.getProductId());
        assertEquals("EUR", priceResponseDto.getCurrency());
        assertThat(priceResponseDto.getValue(), Matchers.comparesEqualTo(BigDecimal.valueOf(35.50)));
    }

    /**
     * Test to verify response in test case #4:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-15T10:00</li>
     * </ul>
     */
    @Test
    void testCaseFour() {
        PriceResponseDto priceResponseDto = this.restTemplate.getForObject(testUrl + "2020-06-15T10:00", PriceResponseDto.class);
        assertEquals("3", priceResponseDto.getPriceListId());
        assertEquals("1", priceResponseDto.getBrandId());
        assertEquals(LocalDateTime.of(2020,06,15,00,00), priceResponseDto.getStartDate());
        assertEquals(LocalDateTime.of(2020,06,15,11,00), priceResponseDto.getEndDate());
        assertEquals("35455", priceResponseDto.getProductId());
        assertEquals("EUR", priceResponseDto.getCurrency());
        assertThat(priceResponseDto.getValue(), Matchers.comparesEqualTo(BigDecimal.valueOf(30.50)));
    }

    /**
     * Test to verify response in test case #5:
     * <ul>
     *     <li>brandId=1</li>
     *     <li>productId=35455</li>
     *     <li>atTime=2020-06-16T21:00</li>
     * </ul>
     */
    @Test
    void testCaseFive() {
        PriceResponseDto priceResponseDto = this.restTemplate.getForObject(testUrl + "2020-06-16T21:00", PriceResponseDto.class);
        assertEquals("4", priceResponseDto.getPriceListId());
        assertEquals("1", priceResponseDto.getBrandId());
        assertEquals(LocalDateTime.of(2020,06,15,16,00), priceResponseDto.getStartDate());
        assertEquals(LocalDateTime.of(2020,12,31,23,59, 59), priceResponseDto.getEndDate());
        assertEquals("35455", priceResponseDto.getProductId());
        assertEquals("EUR", priceResponseDto.getCurrency());
        assertThat(priceResponseDto.getValue(), Matchers.comparesEqualTo(BigDecimal.valueOf(38.95)));
    }

}
