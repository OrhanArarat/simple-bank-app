package com.orhanararat.exception;

import com.orhanararat.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(OutOfBalanceException.class)
    public ResponseEntity<Object> handleRevisionExceptionHandler(OutOfBalanceException ex, WebRequest request) {
        ErrorMessage message = ErrorMessage
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
