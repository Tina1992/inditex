package com.ecommerce.inditex.infrastructure.rest.exception.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Unit test for the {@link RestExceptionsHandler}.
 */
class RestExceptionsHandlerTest {

    /**
     * The {@link RestExceptionsHandler} to test.
     */
    private RestExceptionsHandler restExceptionsHandler;

    /**
     * Test setup.
     */
    @BeforeEach
    public void setUp() {
        restExceptionsHandler = new RestExceptionsHandler();
    }

    /**
     * Unit test for the happy path of {@code RestExceptionsHandler#handleNoResultException(NoResultFoundException, WebRequest)}
     */
    @Test
    void handleNoResultException() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRequestURI("URI-example");
        mockHttpServletRequest.setParameter("applied_time", "applied_time");
        ServletWebRequest servletWebRequest = new ServletWebRequest(mockHttpServletRequest);
        String expectedBody = "No results were found for request to URI-example with parameter applied_time";
        NoResultFoundException noResultFoundException = new NoResultFoundException();

        ResponseEntity responseEntity = restExceptionsHandler.handleNoResultException(noResultFoundException, servletWebRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(expectedBody, responseEntity.getBody().toString());
    }
}