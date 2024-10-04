package com.backend.iLearn.common.mapper;

import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.dto.CreateAdminDto;
import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.auth.entity.User;
import jakarta.annotation.Nullable;

public class AdminMapper {
    public static AdminDto toDto(Admin admin) {
        if (admin == null) {
            return null;
        }

        return AdminDto.builder()
                .id(admin.getId())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .user(admin.getUser() != null ? admin.getUser() : null)
                .build();
    }

    public static Admin toEntity( User user) {
        return Admin.builder()
                .user(user)
                .build();
    }
}
