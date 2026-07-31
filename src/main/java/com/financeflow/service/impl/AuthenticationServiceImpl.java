package com.financeflow.service.impl;

import com.financeflow.dto.auth.LoginRequest;
import com.financeflow.dto.auth.LoginResponse;
import com.financeflow.entity.User;
import com.financeflow.exception.custom.InvalidCredentialsException;
import com.financeflow.mapper.UserMapper;
import com.financeflow.repository.UserRepository;
import com.financeflow.security.JwtService;
import com.financeflow.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(token)
                .build();
        }
    }

