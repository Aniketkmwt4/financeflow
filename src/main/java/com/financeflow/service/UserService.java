package com.financeflow.service;

import com.financeflow.dto.profile.ChangePasswordRequest;
import com.financeflow.dto.profile.ProfileResponse;
import com.financeflow.dto.profile.UpdateProfileRequest;
import com.financeflow.dto.user.RegisterRequest;
import com.financeflow.dto.user.UserResponse;
import com.financeflow.entity.User;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);

    ProfileResponse getProfile(User user);

    ProfileResponse updateProfile(User user, UpdateProfileRequest request);

    void changePassword(User user, ChangePasswordRequest request);

}