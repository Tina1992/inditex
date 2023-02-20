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

@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { NoResultFoundException.class })
    protected ResponseEntity<Object> handleNoResultException(NoResultFoundException ex, WebRequest request) {
        HttpServletRequest servletWebRequest = ((ServletWebRequest) request).getRequest();
        String bodyOfResponse = String.format("No results were found for request to %s with parameter %s", servletWebRequest.getRequestURI(), servletWebRequest.getParameter("applied_time"));
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
