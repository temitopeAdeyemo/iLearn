package com.backend.iLearn.modules.auth.service;

import com.backend.iLearn.common.exceptions.CredentialExistsException;
import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.auth.Enum.Role;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.RoleEntity;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.auth.repository.RoleRepository;
import com.backend.iLearn.modules.auth.repository.UserRepository;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // ****************************** ADMINS SHOULD GET INVITED, So refactor code ********************************************
    public IdResponseDto init(AdminDto payload){
        this.userRepository.findByEmail(payload.getEmail()).orElseThrow(()->new CredentialExistsException("Credential Exists. Contact admin to create an account for you."));

        var newAdmin = Admin.builder()
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .build();

        var newUser = new User();

        newUser.setEmail(payload.getEmail());
        newUser.setPassword(payload.getPassword());
        newUser.setAdminProfile(newAdmin);

        var roles = new HashSet<RoleEntity>();
        var adminRole = Role.ADMIN.toString();
        var role = this.roleRepository.findByName(adminRole).orElseThrow(()->new NotFoundException("Role not found."));

        roles.add(role);

        newUser.setRoles(roles);

        this.userRepository.save(newUser);

        return IdResponseDto.builder().id(null).build();
    }

    public IdResponseDto init(StudentDto payload){
        this.userRepository.findByEmail(payload.getEmail()).orElseThrow(()->new CredentialExistsException("Credential Exists. Login and create a student profile if you do not have one."));

        var newStudent = Student.builder()
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .build();

        var newUser = new User();

        newUser.setEmail(payload.getEmail());
        newUser.setPassword(payload.getPassword());
        newUser.setStudentProfile(newStudent);

        var roles = new HashSet<RoleEntity>();
        var studentRole = Role.STUDENT.toString();
        var role = this.roleRepository.findByName(studentRole).orElseThrow(()->new NotFoundException("Role not found."));

        roles.add(role);

        newUser.setRoles(roles);

        this.userRepository.save(newUser);

        return IdResponseDto.builder().id(null).build();
    }

    public IdResponseDto init(TutorDto payload){
        this.userRepository.findByEmail(payload.getEmail()).orElseThrow(()->new CredentialExistsException("Credential Exists. Login and create a tutor profile if you do not have one."));

        var newTutor = Tutor.builder()
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .build();

        var newUser = new User();

        newUser.setEmail(payload.getEmail());
        newUser.setPassword(payload.getPassword());
        newUser.setTutorProfile(newTutor);

        var roles = new HashSet<RoleEntity>();
        var tutorRole = Role.TUTOR.toString();
        var role = this.roleRepository.findByName(tutorRole).orElseThrow(()->new NotFoundException("Role not found."));

        roles.add(role);

        newUser.setRoles(roles);

        this.userRepository.save(newUser);

        return IdResponseDto.builder().id(null).build();
    }
}
