package com.backend.iLearn.common.exceptions;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.resource.NoResourceFoundException;

public class ResourceNotFoundException extends NoResourceFoundException {

    public ResourceNotFoundException(HttpMethod httpMethod, String resourcePath) {
        super(httpMethod, resourcePath);
    }
}
