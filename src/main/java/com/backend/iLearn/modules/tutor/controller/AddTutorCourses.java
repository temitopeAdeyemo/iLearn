package com.backend.iLearn.modules.tutor.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.tutor.dto.TutorCourseDto;
import com.backend.iLearn.modules.tutor.service.AddTutorCoursesService;
import com.backend.iLearn.modules.tutor.service.AddTutorProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tutor/course")
public class AddTutorCourses {
    private final AddTutorCoursesService addTutorCoursesService;
    @PostMapping("/")
    public ResponseEntity<ApiResponse<IdResponseDto>> addTutorCourse( @Valid  @RequestBody TutorCourseDto payload){
        var resp = this.addTutorCoursesService.exec(payload);
        return new ResponseEntity<>(new ApiResponse<>("Created", resp), HttpStatus.CREATED);
    }
}
