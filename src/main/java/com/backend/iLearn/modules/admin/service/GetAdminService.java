package com.backend.iLearn.modules.admin.service;

import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.dto.GetAdminByUniqueFieldDto;
import com.backend.iLearn.modules.admin.entity.Admin;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class GetAdminService {
    public Admin getOne( GetAdminByUniqueFieldDto payload){
        return null;
    }

    public HashSet<Admin> getMany(AdminDto filter, PaginationRequest pageData){
        return null;
    }
}
