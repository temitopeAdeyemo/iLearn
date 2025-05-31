package com.backend.iLearn.modules.admin.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.mapper.AdminMapper;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.admin.repository.AdminRepository;

import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAdminService {
    private final AdminRepository adminRepository;
    public AdminDto getOne(UUID id){
        System.out.println(id);
        var admin = this.adminRepository.findById(id).orElseThrow(()-> new NotFoundException("Admin Not Found"));

        return AdminMapper.toDto(admin);
    }

    public HashSet<AdminDto> getMany(AdminDto filter, PaginationRequest pageData){

        PageRequest pageable = PageRequest.of(pageData.getPage(), pageData.getSize(), Sort.by("createdAt").descending());
        Page<Admin> admins = this.adminRepository.findAll(pageable);

        HashSet<AdminDto> adminResponse = new HashSet<>();
        admins.forEach((admin -> {
            System.out.println(admin.getReceivedChats());
//            User user = admin.getUser();
            var adminDtoData = AdminDto.builder()
                    .id(admin.getId())
                    .firstName(admin.getUser().getFirstName())
                    .lastName(admin.getUser().getLastName())
                    .email(admin.getUser().getEmail())
                    .createdAt(admin.getCreatedAt())
                    .updatedAt(admin.getUpdatedAt())
                    .build();
//            UserDto userDto = UserDto.builder()
//                    .id(user.getId())
//                    .firstName(user.getFirstName())
//                    .lastName(user.getLastName())
//                    .email(user.getEmail())
//                    .build();
//
//            AdminDto adminDtoData = AdminDto.builder()
//                    .id(admin.getId())
//                    .user(userDto)
//                    .createdAt(admin.getCreatedAt())
//                    .updatedAt(admin.getUpdatedAt())
//                    .build();

            adminResponse.add(adminDtoData);
        }));

        return adminResponse;
    }
}
