package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.service.DeleteCourseVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/video/course")
@RequiredArgsConstructor
public class DeleteCourseVideo {
    public DeleteCourseVideoService deleteCourseVideoService;
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(@PathVariable(value = "id") String id){
         this.deleteCourseVideoService.exec(id);
        return new ResponseEntity<>( new ApiResponse<>("Course video deleted successfully", null), HttpStatus.OK);
    }
}
