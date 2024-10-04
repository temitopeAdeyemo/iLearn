package com.backend.iLearn.modules.admin.service;

import com.backend.iLearn.modules.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RemoveAdminAccessService {
    private final AdminRepository adminRepository;
    public void exec(UUID id) {
        this.adminRepository.deleteById(id);
    }
}
