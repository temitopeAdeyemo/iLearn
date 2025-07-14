package com.backend.iLearn.modules.course.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseContentDto;
import com.backend.iLearn.modules.course.dto.CreateCourseContentResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseTextContentDto;
import com.backend.iLearn.modules.course.dto.CreateCourseVideoContentDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.entity.CourseContent;
import com.backend.iLearn.modules.course.repository.CourseRepository;
import com.backend.iLearn.modules.course.repository.CourseContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateCourseContentService {
    private final CourseRepository courseRepository;
    private final CreateCourseVideoContentService createCourseVideoContentService;
    private final CreateCourseTextContentService createCourseTextContentService;
    public CreateCourseContentResponseDto exec(CreateCourseContentDto payload){
        Course course = this.courseRepository.findById(UUID.fromString(payload.getCourseId())).orElseThrow(()->new NotFoundException("Course not found"));

        Set<CreateCourseVideoContentDto> videoData = payload.getVideos();
        Set<CreateCourseTextContentDto> contentText = payload.getTexts();

        var responseBuilder = CreateCourseContentResponseDto.builder();
        if (videoData != null && !videoData.isEmpty()) {
            var videoIdList = this.createCourseVideoContentService.exec(course, videoData);
            responseBuilder.videoData(videoIdList);
        }

        if (contentText != null && !contentText.isEmpty()) {
            var contentTextIdList = this.createCourseTextContentService.exec(course, contentText);
            responseBuilder.textData(contentTextIdList);
        }

        return responseBuilder.build();
    }
}
