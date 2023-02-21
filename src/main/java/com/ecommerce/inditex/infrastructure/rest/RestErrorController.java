package com.ecommerce.inditex.infrastructure.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Error controller.
 */
@RestController
public class RestErrorController implements ErrorController {

    /**
     * Handle error function.
     * @param request request
     * @return the {@link ResponseEntity} for errors
     */
    @RequestMapping("/error")
    public ResponseEntity handleError(final HttpServletRequest request) {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String body = String.format("Internal error. Cause: %s", throwable.getMessage());
        return new ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
