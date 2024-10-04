package com.backend.iLearn.modules.student.service;

import com.backend.iLearn.common.exceptions.CredentialExistsException;
import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.mapper.AdminMapper;
import com.backend.iLearn.common.mapper.StudentMapper;
import com.backend.iLearn.modules.auth.Enum.Role;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.auth.repository.RoleRepository;
import com.backend.iLearn.modules.auth.repository.UserRepository;
import com.backend.iLearn.modules.student.dto.CreateStudentDto;
import com.backend.iLearn.modules.tutor.dto.CreateTutorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddStudentProfileService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public IdResponseDto exec(User user) {
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(Role.STUDENT.name()))) {
            throw new CredentialExistsException("Student profile already exists for user.");
        }

        var adminRole = Role.STUDENT.toString();

        var role = this.roleRepository.findByName(adminRole).orElseThrow(() -> new NotFoundException("Role not found."));

        user.getRoles().add(role);

        var studentData = StudentMapper.toEntity( user);

        user.setStudentProfile(studentData);
        user.getStudentProfile().setUser(user);

        var newStudent = this.userRepository.save(user);

        return IdResponseDto.builder().id(newStudent.getId()).build();
    }
}
