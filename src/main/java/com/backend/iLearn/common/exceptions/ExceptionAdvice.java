package com.backend.iLearn.common.exceptions;

import com.backend.iLearn.common.utils.ApiException;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ApiException<>("Resource Not Found", null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CredentialExistsException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleCredentialExistsException(CredentialExistsException ex){
        return new ResponseEntity<>(new ApiException<>(ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(new ApiException<>(ex.getMessage(), null), HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(value = {HibernateException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleHibernateException(HibernateException ex){
        return new ResponseEntity<>(new ApiException<>(ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiException<Object>> handleInvalidArgument(HttpRequestMethodNotSupportedException ex){
        return new ResponseEntity<>( new ApiException<>("Page Not Found", null), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiException<Object>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
        return new ResponseEntity<>( new ApiException<>("Invalid Parameter", null), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException<Map<String, String>>> handleAllExceptions(Exception ex) {
        System.out.println("Exception: " + ex);
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException<Map<String, String>> apiException = new ApiException<>(ex.getMessage(), null);

        return new ResponseEntity<>(apiException, badRequest);
    }
}
