package com.ecommerce.inditex.infrastructure.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.RequestDispatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link RestErrorController}.
 */
class RestErrorControllerTest {

    /**
     * The {@link RestErrorController} to test.
     */
    private RestErrorController restErrorController;

    /**
     * Test setUp
     */
    @BeforeEach
    public void setUp() {
        restErrorController = new RestErrorController();
    }

    /**
     * Test happy path for error controller
     */
    @Test
    void handleError() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute(RequestDispatcher.ERROR_EXCEPTION, new RuntimeException("Error cause"));

        ResponseEntity response = restErrorController.handleError(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal error. Cause: Error cause", response.getBody());
    }
}