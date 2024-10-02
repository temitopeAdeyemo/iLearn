package com.backend.iLearn.common.utils;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaginationRequest {

//    @NotNull(message = "Page number cannot be null")
    @Min(value = 0, message = "Page number must be 0 or greater")
    private Integer page = 0;

//    @NotNull(message = "Page size cannot be null")
    @Min(value = 1, message = "Page size must be at least 1")
    private Integer size = 10;
}
