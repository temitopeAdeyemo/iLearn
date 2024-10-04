package com.backend.iLearn.modules.tutor.service;

import com.backend.iLearn.common.exceptions.CredentialExistsException;
import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.mapper.StudentMapper;
import com.backend.iLearn.common.mapper.TutorMapper;
import com.backend.iLearn.modules.auth.Enum.Role;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.auth.repository.RoleRepository;
import com.backend.iLearn.modules.auth.repository.UserRepository;
import com.backend.iLearn.modules.tutor.dto.CreateTutorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTutorProfileService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public IdResponseDto exec(User user) {
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(Role.TUTOR.name()))) {
            throw new CredentialExistsException("Tutor profile already exists for user.");
        }

        var adminRole = Role.TUTOR.toString();

        var role = this.roleRepository.findByName(adminRole).orElseThrow(() -> new NotFoundException("Role not found."));

        user.getRoles().add(role);

        var tutorData = TutorMapper.toEntity(user);

        user.setTutorProfile(tutorData);
        user.getTutorProfile().setUser(user);

        var newTutor = this.userRepository.save(user);

        return IdResponseDto.builder().id(newTutor.getId()).build();
    }
}
