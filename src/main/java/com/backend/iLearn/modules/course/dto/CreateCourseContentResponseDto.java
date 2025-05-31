package com.backend.iLearn.modules.course.dto;

import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseContentResponseDto {
    private List<IdResponseDto> videoData;
    private List<IdResponseDto> textData;
}

