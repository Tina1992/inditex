package com.ecommerce.inditex.infrastructure.rest.exception.handler;

import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static com.ecommerce.inditex.infrastructure.rest.utils.RestConstants.APPLIED_TIME_PARAMETER_NAME;

/**
 * Exception handler.
 */
@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the {@link NoResultFoundException} to return a legible error.
     * @param exception exception
     * @param request request
     * @return the {@link ResponseEntity}
     */
    @ExceptionHandler(value = NoResultFoundException.class)
    protected ResponseEntity<Object> handleNoResultException(final NoResultFoundException exception, final WebRequest request) {
        HttpServletRequest servletWebRequest = ((ServletWebRequest) request).getRequest();
        String bodyOfResponse = String.format("No results were found for request to %s with parameter %s", servletWebRequest.getRequestURI(), servletWebRequest.getParameter(APPLIED_TIME_PARAMETER_NAME));
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
