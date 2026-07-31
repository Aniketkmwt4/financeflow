package com.financeflow.controller;

import com.financeflow.dto.auth.LoginRequest;
import com.financeflow.dto.auth.LoginResponse;
import com.financeflow.dto.user.RegisterRequest;
import com.financeflow.dto.user.UserResponse;
import com.financeflow.response.ApiResponse;
import com.financeflow.service.UserService;
import com.financeflow.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = userService.registerUser(request);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "User registered successfully",
                        response
                )
        );
    }


}