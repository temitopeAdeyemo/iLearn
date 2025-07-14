package com.backend.iLearn.common.exceptions;

import com.backend.iLearn.common.responses.ApiException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@ControllerAdvice
public class ExceptionAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleResourceNotFoundException(ResourceNotFoundException ex){
        System.out.println("******************************************1");
        return new ResponseEntity<>(new ApiException<>("Resource Not Found", null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleCredentialExistsException(HttpMessageNotReadableException ex){
        System.out.println("******************************************12");
        return new ResponseEntity<>(new ApiException<>("Required request body is missing.", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CredentialExistsException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleCredentialExistsException(CredentialExistsException ex){
        System.out.println("******************************************3");
        return new ResponseEntity<>(new ApiException<>(ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleNotFoundException(NotFoundException ex){
        System.out.println("******************************************4");
        return new ResponseEntity<>(new ApiException<>(ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiException<String>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        System.out.println("******************************************5");
        System.out.println("MethodArgumentNotValidException: " + ex.getMessage());

        AtomicReference<String> messageRef = new AtomicReference<>("Validation failed.");

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            messageRef.set(fieldErrors.getFirst().getDefaultMessage());
        } else {
            ex.getBindingResult().getGlobalErrors().stream().findFirst()
                    .ifPresent(error -> messageRef.set(error.getDefaultMessage()));
        }

        ApiException<String> apiException = new ApiException<>(messageRef.get(), null);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {HibernateException.class})
    public ResponseEntity<ApiException<ApiException<Object>>> handleHibernateException(HibernateException ex){
        System.out.println("******************************************");
        return new ResponseEntity<>(new ApiException<>(ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiException<Object>> handleInvalidArgument(HttpRequestMethodNotSupportedException ex){
        System.out.println("******************************************");
        return new ResponseEntity<>( new ApiException<>("Page Not Found", null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiException<Object>> handleBadCredentialsException(BadCredentialsException ex){
        System.out.println("******************************************");
        return new ResponseEntity<>( new ApiException<>("Invalid Credentials.", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiException<Object>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
        System.out.println("******************************************");
        return new ResponseEntity<>( new ApiException<>("Invalid Parameter.", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException<Map<String, String>>> handleAllExceptions(Exception ex) {        System.out.println("******************************************");
        System.out.println("Exception: " + ex);
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException<Map<String, String>> apiException = new ApiException<>(ex.getMessage(), null);

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiException<String>> handleTransactionException(TransactionSystemException ex) {
        System.out.println("******************************************");
        Throwable cause = ex.getRootCause();
        var message = "Unexpected error occurred.";
        if (cause instanceof ConstraintViolationException violationException) {
            for (ConstraintViolation<?> violation : violationException.getConstraintViolations()) {
                message = violation.getMessage();
                break;
            }
            return new ResponseEntity<>(new ApiException<>(message, null), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiException<>(message, null));
    }
}