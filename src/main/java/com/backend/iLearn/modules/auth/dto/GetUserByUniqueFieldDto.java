package com.backend.iLearn.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class GetUserByUniqueFieldDto {
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Id must be type uuid.")
    public UUID id;

    @Email
    public String email;
}