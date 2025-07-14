package com.backend.iLearn.modules.course.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseTextContentDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.entity.TextContent;
import com.backend.iLearn.modules.course.repository.CourseRepository;
import com.backend.iLearn.modules.course.repository.TextContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateCourseTextContentService {
    private final CourseRepository courseRepository;
    private final TextContentRepository textContentRepository;
    public List<IdResponseDto> exec(Course course,  Set<CreateCourseTextContentDto> payloadSet){
        List<IdResponseDto> idList = new ArrayList<>();
        var courseTextEntities = payloadSet.stream().map((payload)->{
            TextContent courseTexts = new TextContent();

            courseTexts.setContent(payload.getContent());
            courseTexts.setTitle(payload.getTitle());
            courseTexts.setCourse(course);
            courseTexts.setSequenceNumber(payload.getSequenceNumber());

            idList.add(IdResponseDto.builder().id(courseTexts.getId()).build());
            return courseTexts;
        }).collect(Collectors.toSet());

        this.textContentRepository.saveAll(courseTextEntities);

        return idList;
    }
}
