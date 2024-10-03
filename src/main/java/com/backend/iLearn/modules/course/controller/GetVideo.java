package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.course.dto.CourseVideoDto;
import com.backend.iLearn.modules.course.service.GetCourseVideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course/video-resource")
@RequiredArgsConstructor
public class GetVideo {
    public GetCourseVideosService getCourseVideoService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseVideoDto>> init(@PathVariable(value = "id") String id){
        CourseVideoDto response = this.getCourseVideoService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Course fetched in successfully", response), HttpStatus.OK);
    }
}
