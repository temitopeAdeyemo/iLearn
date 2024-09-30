package com.backend.iLearn.common.exceptions;

public class CredentialExistsException extends RuntimeException{
    public CredentialExistsException(String message){
        super(message);
    }
}