package com.backend.iLearn.modules.auth.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.mapper.UserMapper;
import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final UserRepository userRepository;

    public UserDto exec(UUID id){
        var user = this.userRepository.findById(id).orElseThrow(()->new NotFoundException("User Not Found."));

        return UserMapper.toDto(user);
    }
}
