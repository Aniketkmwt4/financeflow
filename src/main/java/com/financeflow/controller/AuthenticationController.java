package com.financeflow.controller;

import com.financeflow.dto.auth.LoginRequest;
import com.financeflow.dto.auth.LoginResponse;
import com.financeflow.response.ApiResponse;
import com.financeflow.service.AuthenticationService;
import com.financeflow.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authenticationService.login(request);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Login successful",
                        response
                )
        );
    }

}


