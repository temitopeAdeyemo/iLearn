package com.backend.iLearn.modules.student.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.mapper.StudentMapper;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetStudentService {
    private final StudentRepository studentRepository;
    public StudentDto getOne(UUID id){
        System.out.println(id);
        var student = this.studentRepository.findById(id).orElseThrow(()-> new NotFoundException("Student Not Found"));

        return StudentMapper.toDto(student);
    }

    public HashSet<StudentDto> getMany(StudentDto filter, PaginationRequest pageData){
        PageRequest pageable = PageRequest.of(pageData.getPage(), pageData.getSize(), Sort.by("createdAt").descending());
        Page<Student> students = this.studentRepository.findAll(pageable);

        HashSet<StudentDto> studentResponse = new HashSet<>();
        students.forEach((student -> {
            System.out.println(student.getReceivedChats());
            var a = StudentDto.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .createdAt(student.getCreatedAt())
                    .updatedAt(student.getUpdatedAt())
                    .build();

            studentResponse.add(a);
        }));

        return studentResponse;
    }
}
