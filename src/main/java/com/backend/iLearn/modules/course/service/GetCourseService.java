package com.backend.iLearn.modules.course.service;

import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.course.dto.CourseDto;
import com.backend.iLearn.modules.course.dto.CourseVideoDto;
import com.backend.iLearn.modules.course.entity.Course;

import java.util.HashSet;

public class GetCourseService {
    public CourseDto getOne(String id){
        return null;
    }

    public HashSet<CourseDto> getMany(CourseDto filter, PaginationRequest pageData){
        return null;
    }

    public HashSet<CourseDto> search(String keyword/*, PaginationRequest pageData*/){
        return null;
    }
}
