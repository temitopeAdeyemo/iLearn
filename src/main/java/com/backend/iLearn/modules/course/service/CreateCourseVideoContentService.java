package com.backend.iLearn.modules.course.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseVideoContentDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.entity.CourseContent;
import com.backend.iLearn.modules.course.entity.TextContent;
import com.backend.iLearn.modules.course.entity.VideoContent;
import com.backend.iLearn.modules.course.repository.CourseRepository;
import com.backend.iLearn.modules.course.repository.CourseContentRepository;
import com.backend.iLearn.modules.course.repository.VideoContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateCourseVideoContentService {
    private final CourseRepository courseRepository;
    private final VideoContentRepository videoContentRepository;
    public List<IdResponseDto> exec(Course course, Set<CreateCourseVideoContentDto> payloadSet){
        List<IdResponseDto> idList = new ArrayList<>();
        var courseTextEntities = payloadSet.stream().map((payload)->{
            VideoContent courseVideos = new VideoContent();

            courseVideos.setUrl(payload.getUrl());
            courseVideos.setTitle(payload.getTitle());
            courseVideos.setCourse(course);
            courseVideos.setSequenceNumber(payload.getSequenceNumber());

            idList.add(IdResponseDto.builder().id(courseVideos.getId()).build());
            return courseVideos;
        }).collect(Collectors.toSet());

        this.videoContentRepository.saveAll(courseTextEntities);

        return idList;
    }
}
