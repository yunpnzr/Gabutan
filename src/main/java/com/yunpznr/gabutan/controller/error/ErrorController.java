package com.yunpznr.gabutan.controller.error;

import com.yunpznr.gabutan.model.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.eclipse.angus.mail.smtp.SMTPAddressFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<WebResponse<ErrorResponse>> unauthorizedException(BadCredentialsException e){
        return ResponseEntity.status(401).body(
                WebResponse.<ErrorResponse>builder()
                        .statusCode(401)
                        .message(e.getMessage())
                        .data(null)
                        .build()
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<ErrorResponse>> responseStatusException(ResponseStatusException e){
        return ResponseEntity.status(e.getStatusCode()).body(
                WebResponse.<ErrorResponse>builder()
                        .statusCode(e.getStatusCode().value())
                        .message(e.getReason())
                        .data(null)
                        .build()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<WebResponse<ErrorResponse>> illegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(400).body(
                WebResponse.<ErrorResponse>builder()
                        .statusCode(400)
                        .message(e.getMessage())
                        .data(null)
                        .build()
        );
    }

    @ExceptionHandler(SMTPAddressFailedException.class)
    public ResponseEntity<WebResponse<ErrorResponse>> smtpAddressFailedException(SMTPAddressFailedException e){
        return ResponseEntity.status(400).body(
                WebResponse.<ErrorResponse>builder()
                        .statusCode(400)
                        .message(e.getMessage())
                        .data(null)
                        .build()
        );
    }
}
