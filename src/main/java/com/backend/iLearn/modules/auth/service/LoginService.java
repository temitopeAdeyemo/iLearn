package com.backend.iLearn.modules.auth.service;

import com.backend.iLearn.modules.admin.dto.AccessTokenDto;
import com.backend.iLearn.modules.admin.dto.AdminLoginDto;
import com.backend.iLearn.modules.student.dto.StudentLoginDto;
import com.backend.iLearn.modules.tutor.dto.TutorLoginDto;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public AccessTokenDto exec(AdminLoginDto payload){
        return AccessTokenDto.builder().access_token("").build();
    }

    public AccessTokenDto exec( TutorLoginDto payload){
        return AccessTokenDto.builder().access_token("").build();
    }

    public AccessTokenDto exec( StudentLoginDto payload){
        return AccessTokenDto.builder().access_token("").build();
    }

}
