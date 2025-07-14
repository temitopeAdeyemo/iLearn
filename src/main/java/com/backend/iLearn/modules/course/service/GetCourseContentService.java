package com.backend.iLearn.modules.course.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.modules.course.dto.CourseContentResponseDto;
import com.backend.iLearn.modules.course.entity.ContentType;
import com.backend.iLearn.modules.course.entity.CourseContent;
import com.backend.iLearn.modules.course.entity.TextContent;
import com.backend.iLearn.modules.course.entity.VideoContent;
import com.backend.iLearn.modules.course.repository.CourseContentRepository;
import com.backend.iLearn.modules.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetCourseContentService {
    private final CourseContentRepository courseContentRepository;
    private final CourseRepository courseRepository;
    public Page<CourseContentResponseDto> getContent(String courseId, ContentType contentType, Integer page, Integer size){
        var course = this.courseRepository.findById(UUID.fromString(courseId)).orElseThrow(()->new NotFoundException("Course Not Found."));

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("createdAt")));
        var contents = this.courseContentRepository.findByCourse(course, pageRequest);

        List<CourseContentResponseDto> res = new ArrayList<>();

        if(contentType == ContentType.ALL) {
            res = contents.stream().map((content) -> {
                var response = new CourseContentResponseDto();
                if (content instanceof VideoContent) {
                    response.setContent(((VideoContent) content).getUrl());
                }
                if (content instanceof TextContent) {
                    response.setContent(((TextContent) content).getContent());
                }

                mapCommonFields(response, content);

                return response;
            }).toList();
        } else if(contentType == ContentType.VIDEO){
            res = contents.stream().map((content) -> {
                var response = new CourseContentResponseDto();
                if (content instanceof VideoContent) {
                    response.setContent(((VideoContent) content).getUrl());
                }

                mapCommonFields(response, content);

                return response;
            }).toList();
        }else if(contentType == ContentType.TEXT){
            res = contents.stream().map((content) -> {
                var response = new CourseContentResponseDto();
                if (content instanceof TextContent) {
                    response.setContent(((TextContent) content).getContent());
                }

                mapCommonFields(response, content);
                return response;
            }).toList();
        }

        return new PageImpl<>( new ArrayList<>(),pageRequest, res.size());
    }

    private void mapCommonFields(CourseContentResponseDto response, CourseContent content){
        response.setId(content.getId());
        response.setTitle(content.getTitle());
        response.setSequenceNumber(content.getSequenceNumber());
        response.setCreatedAt(content.getCreatedAt().toString());
        response.setUpdatedAt(content.getUpdatedAt().toString());
    }

    public CourseContentResponseDto getOne(String id) {
        var content = this.courseContentRepository.findById(UUID.fromString(id)).orElseThrow(()-> new NotFoundException("Content Not Found"));
        var response = new CourseContentResponseDto();
        if (content instanceof VideoContent) {
            response.setContent(((VideoContent) content).getUrl());
        } else if (content instanceof TextContent) {
            response.setContent(((TextContent) content).getContent());
        }

        mapCommonFields(response, content);

        return response;
    }
}
