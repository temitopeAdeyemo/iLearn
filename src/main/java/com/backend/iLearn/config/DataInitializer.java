package com.backend.iLearn.config;


import com.backend.iLearn.modules.auth.Enum.Role;
import com.backend.iLearn.modules.auth.entity.RoleEntity;
import com.backend.iLearn.modules.auth.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer {
    @Bean
    public CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            Arrays.stream(Role.values())
                    .forEach(roleEnum -> createRoleIfNotExists(roleRepository, roleEnum.name()));
        };
    }

    private void createRoleIfNotExists(RoleRepository roleRepository, String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            RoleEntity newRole = new RoleEntity();
            newRole.setName(roleName);
            roleRepository.save(newRole);
            System.out.println("Created Role: " + newRole.getName());
        }
    }

}
