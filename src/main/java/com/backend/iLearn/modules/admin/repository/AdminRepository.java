package com.backend.iLearn.modules.admin.repository;

import com.backend.iLearn.modules.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
