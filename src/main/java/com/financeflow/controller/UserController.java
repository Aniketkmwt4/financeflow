package com.financeflow.controller;

import com.financeflow.dto.profile.ChangePasswordRequest;
import com.financeflow.dto.profile.ProfileResponse;
import com.financeflow.dto.profile.UpdateProfileRequest;
import com.financeflow.dto.user.RegisterRequest;
import com.financeflow.dto.user.UserResponse;
import com.financeflow.entity.User;
import com.financeflow.response.ApiResponse;
import com.financeflow.service.UserService;
import com.financeflow.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseUtil.success(
                        "User registered successfully",
                        response
                )
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(
            @AuthenticationPrincipal User user
    ) {

        ProfileResponse response = userService.getProfile(user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Profile fetched successfully",
                        response
                )
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody UpdateProfileRequest request
    ) {

        ProfileResponse response = userService.updateProfile(user, request);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Profile updated successfully",
                        response
                )
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody ChangePasswordRequest request
    ) {

        userService.changePassword(user, request);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Password changed successfully",
                        null
                )
        );
    }


}