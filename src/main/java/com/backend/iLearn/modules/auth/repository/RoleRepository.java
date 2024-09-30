package com.backend.iLearn.modules.auth.repository;

import com.backend.iLearn.modules.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(String role_name);

    Optional<RoleEntity> findByName(String roleName);
}
