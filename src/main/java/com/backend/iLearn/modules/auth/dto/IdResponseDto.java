package com.backend.iLearn.modules.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
public class IdResponseDto {
    public UUID id;
}
