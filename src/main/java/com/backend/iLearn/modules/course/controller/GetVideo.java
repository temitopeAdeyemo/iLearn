package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.course.dto.CourseContentResponseDto;
import com.backend.iLearn.modules.course.dto.CourseVideoContentDto;
import com.backend.iLearn.modules.course.entity.ContentType;
import com.backend.iLearn.modules.course.service.GetCourseContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course/video-resource")
@RequiredArgsConstructor
public class GetVideo {
    public GetCourseContentService getCourseVideoService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseContentResponseDto>> init(@PathVariable(value = "id") String id){
        CourseContentResponseDto response = this.getCourseVideoService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Course fetched in successfully", response), HttpStatus.OK);
    }

    @GetMapping("/{courseId}/contents")
    public ResponseEntity<ApiResponse<Page<CourseContentResponseDto>>> getCourseContents(
            @PathVariable String courseId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ALL") ContentType type) {
        return new ResponseEntity<>( new ApiResponse<>("Course fetched in successfully",getCourseVideoService.getContent(courseId, type, page, size)), HttpStatus.OK);
    }
}
