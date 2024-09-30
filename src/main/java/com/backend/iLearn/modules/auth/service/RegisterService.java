package com.backend.iLearn.modules.auth.service;

import com.backend.iLearn.common.exceptions.CredentialExistsException;
import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.admin.repository.AdminRepository;
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
    private final AdminRepository adminRepository;

    public IdResponseDto init(AdminDto payload) {
        System.out.println(payload.getEmail());

        // Check if user with the provided email already exists
        var user = this.userRepository.findByEmail(payload.getEmail());
        if (user.isPresent()) {
            System.out.println(user.get().getPassword());
            throw new CredentialExistsException("Credential Exists. Contact admin to create an account for you.");
        }

        var newUser = new User();

        // Create a new Admin instance
        var newAdmin = new Admin();
        newAdmin.setFirstName(payload.getFirstName());
        newAdmin.setLastName(payload.getLastName());
        var newUser = new User();
        newAdmin.setUserId(newUser);

//        this.adminRepository.save(newAdmin);

        newUser.setEmail(payload.getEmail());
        newUser.setPassword(payload.getPassword());

        var roles = new HashSet<RoleEntity>();
        var adminRole = Role.ADMIN.toString();
        var role = this.roleRepository.findByName(adminRole).orElseThrow(() -> new NotFoundException("Role not found."));
        roles.add(role);

        newUser.setRoles(roles);

        // Save the user (and admin will be saved automatically due to cascading)
//        this.adminRepository.save(newAdmin);
        var dd = this.userRepository.save(newUser);

//        // Create a new Admin instance
//        var newAdmin = new Admin();
//        newAdmin.setFirstName(payload.getFirstName());
//        newAdmin.setLastName(payload.getLastName());
////        var newUser = new User();
//        newAdmin.setUserId(dd);
//
//        this.adminRepository.save(newAdmin);

        return IdResponseDto.builder().id(newUser.getId()).build();
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
