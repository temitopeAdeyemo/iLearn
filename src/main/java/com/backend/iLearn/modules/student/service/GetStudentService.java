package com.backend.iLearn.modules.student.service;

import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.student.entity.Student;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class GetStudentService {
    public Student getOne(UUID id){
        return new Student();
    }

    public HashSet<Student> getMany(StudentDto filter, PaginationRequest pageData){
        return null;
    }
}
