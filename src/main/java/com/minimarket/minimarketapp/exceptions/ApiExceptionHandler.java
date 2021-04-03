package com.minimarket.minimarketapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MiniMarketException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(MiniMarketException ex){
        ApiErrorResponse response = new ApiErrorResponse(ex.getCode(),
                ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatus());
    }
}
