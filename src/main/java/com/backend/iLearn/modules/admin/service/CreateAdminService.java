package com.backend.iLearn.modules.admin.service;

import com.backend.iLearn.common.exceptions.CredentialExistsException;
import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.mapper.AdminMapper;
import com.backend.iLearn.modules.admin.dto.CreateAdminDto;
import com.backend.iLearn.modules.auth.Enum.Role;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.auth.repository.RoleRepository;
import com.backend.iLearn.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public IdResponseDto exec( User user) {
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(Role.ADMIN.name()))) {
            throw new CredentialExistsException("Admin profile already exists for user.");
        }

        var adminRole = Role.ADMIN.toString();

        var role = this.roleRepository.findByName(adminRole).orElseThrow(() -> new NotFoundException("Role not found."));

        user.getRoles().add(role);

        var adminData = AdminMapper.toEntity(user);

        user.setAdminProfile(adminData);
        user.getAdminProfile().setUser(user);

        var newAdmin = this.userRepository.save(user);

        return IdResponseDto.builder().id(newAdmin.getId()).build();
    }
}