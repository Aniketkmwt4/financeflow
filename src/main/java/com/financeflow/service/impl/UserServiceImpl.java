package com.financeflow.service.impl;

import com.financeflow.dto.auth.LoginRequest;
import com.financeflow.dto.auth.LoginResponse;
import com.financeflow.dto.user.RegisterRequest;
import com.financeflow.dto.user.UserResponse;
import com.financeflow.entity.User;
import com.financeflow.enums.Role;
import com.financeflow.exception.custom.InvalidCredentialsException;
import com.financeflow.exception.custom.ResourceAlreadyExistsException;
import com.financeflow.mapper.UserMapper;
import com.financeflow.repository.UserRepository;
import com.financeflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        User user = userMapper.toEntity(
                request,
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

}