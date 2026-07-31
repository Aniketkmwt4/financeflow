package com.financeflow.mapper;

import com.financeflow.dto.auth.LoginResponse;
import com.financeflow.dto.user.RegisterRequest;
import com.financeflow.dto.user.UserResponse;
import com.financeflow.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request, String encodedPassword) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();

    }


}