package com.backend.iLearn.modules.course.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.course.dto.CourseDto;
import com.backend.iLearn.modules.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCourseService {
    private final CourseRepository courseRepository;
    public CourseDto getOne(String id){
        var course = this.courseRepository.findById(UUID.fromString(id)).orElseThrow(()->new NotFoundException("Course not found"));

        return CourseDto.builder()
                .title(course.getTitle())
//                .content(course.getContent())
                .id(course.getId())
                .createdAt(course.getCreatedAt().toString())
                .build();
    }

    public List<CourseDto> getMany(CourseDto filter, PaginationRequest pageData){

        var pageRequestData = PageRequest.of(pageData.getPage(), pageData.getSize(), Sort.by(Sort.Direction.DESC, "createdAt"));
        var courses = this.courseRepository.findAll(pageRequestData);

        return courses.stream().map((course ->
            CourseDto.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .id(course.getId())
                    .createdAt(course.getCreatedAt().toString())
                    .build()
        )).toList();
    }

    public HashSet<CourseDto> search(String keyword/*, PaginationRequest pageData*/){
        return null;
    }
}
