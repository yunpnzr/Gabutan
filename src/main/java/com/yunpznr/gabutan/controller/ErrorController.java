package com.yunpznr.gabutan.controller;

import com.yunpznr.gabutan.model.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<ErrorResponse>> constraintViolationException(ConstraintViolationException e){
        String message = e.getConstraintViolations().iterator().next().getMessage();

        return ResponseEntity.status(400).body(
                WebResponse.<ErrorResponse>builder()
                        .statusCode(400)
                        .message(message)
                        .build()
        );
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<WebResponse<ErrorResponse>> unauthorizedException(HttpClientErrorException.Unauthorized e){
        return ResponseEntity.status(401).body(
                WebResponse.<ErrorResponse>builder()
                        .statusCode(401)
                        .message(e.getMessage())
                        .build()
        );
    }
}
