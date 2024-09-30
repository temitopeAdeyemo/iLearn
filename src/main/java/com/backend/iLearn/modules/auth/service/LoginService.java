package com.backend.iLearn.modules.auth.service;

import com.backend.iLearn.config.JwtService;
import com.backend.iLearn.modules.auth.dto.AccessTokenDto;
import com.backend.iLearn.modules.admin.dto.AdminLoginDto;
import com.backend.iLearn.modules.auth.dto.LoginDto;
import com.backend.iLearn.modules.auth.repository.UserRepository;
import com.backend.iLearn.modules.student.dto.StudentLoginDto;
import com.backend.iLearn.modules.tutor.dto.TutorLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AccessTokenDto exec(LoginDto payload){
       authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.getEmail(),
                        payload.getPassword()
                )
        );

        var user = userRepository.findByEmail(payload.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AccessTokenDto.builder().access_token(jwtToken).build();
    }
}