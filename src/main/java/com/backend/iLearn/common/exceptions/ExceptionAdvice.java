package com.backend.iLearn.common.exceptions;

import com.backend.iLearn.common.utils.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handlePathException(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ApiException<>("Resource Not Found", null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiException<Map<String, String>>> handleInvalidArgument(MethodArgumentNotValidException ex){
        System.out.println("MethodArgumentNotValidException: " + ex);
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(e -> {
            errorMap.put(e.getField(), e.getDefaultMessage());
        });

        ApiException<Map<String, String>> apiException = new ApiException<>("VALIDATION FAILED.", errorMap );

        return new ResponseEntity<>( apiException, HttpStatus.BAD_REQUEST);
    }
}
