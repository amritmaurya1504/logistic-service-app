package com.logistic.orderms.order.exception;

import com.logistic.orderms.order.payload.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ExceptionResponse> handleOrderNotFoundException(OrderNotFoundException ex){
        String msg = ex.getMessage();
        ExceptionResponse apiResponse = ExceptionResponse.builder().success(false).message(msg)
                .status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
