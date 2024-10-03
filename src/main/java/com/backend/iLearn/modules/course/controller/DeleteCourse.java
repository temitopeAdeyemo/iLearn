package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseDto;
import com.backend.iLearn.modules.course.service.DeleteCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class DeleteCourse {
    public DeleteCourseService deleteCourseService;
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(@PathVariable(value = "id") String id){
        this.deleteCourseService.exec(id);
        return new ResponseEntity<>( new ApiResponse<>("Course deleted successfully", null), HttpStatus.OK);
    }
}
