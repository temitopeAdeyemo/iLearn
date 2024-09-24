package com.backend.iLearn.modules.auth.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @Column(name = "first_name")
    @NotNull(message = "First name cannot be null")
    @Size(min = 1, max = 25, message = "First name must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 25, message = "Last name must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

    @Column(name = "email", unique = true)
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one uppercase letter, one lowercase letter, and one special character (@#$%^&+=)")
    private String password;
}
