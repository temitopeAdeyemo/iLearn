package com.backend.iLearn.modules.course.service;

import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseTextContentDto;
import com.backend.iLearn.modules.course.dto.CreateCourseVideoContentDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.entity.TextContent;
import com.backend.iLearn.modules.course.entity.VideoContent;
import com.backend.iLearn.modules.course.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Transactional
public class CreateCourseService {
    private final CourseRepository courseRepository;

    public IdResponseDto exec(CreateCourseDto payload) {
        Course newCourse = new Course();
        newCourse.setTitle(payload.getTitle());
        newCourse.setDescription(payload.getDescription());

        Set<CreateCourseVideoContentDto> videoData = payload.getVideos();
        Set<CreateCourseTextContentDto> contentText = payload.getTexts();

        if (videoData != null && !videoData.isEmpty()) {
            videoData.forEach(dto -> {
                VideoContent videoContent = new VideoContent();
                videoContent.setUrl(dto.getUrl());
                videoContent.setSequenceNumber(dto.getSequenceNumber());
                videoContent.setTitle(dto.getTitle());
                videoContent.setCourse(newCourse);

                newCourse.getCourseContents().add(videoContent);
            });
        }

        if (contentText != null && !contentText.isEmpty()) {
                    contentText.forEach(dto -> {
                TextContent textContent = new TextContent();
                textContent.setContent(dto.getContent());
                textContent.setSequenceNumber(dto.getSequenceNumber());
                textContent.setTitle(dto.getTitle());
                textContent.setCourse(newCourse);

                newCourse.getCourseContents().add(textContent);
            });
        }

        Course savedCourse = courseRepository.save(newCourse);
        return IdResponseDto.builder().id(savedCourse.getId()).build();
        }
    }

