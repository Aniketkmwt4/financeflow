package com.financeflow.service;

import com.financeflow.dto.auth.LoginRequest;
import com.financeflow.dto.auth.LoginResponse;
import com.financeflow.dto.user.RegisterRequest;
import com.financeflow.dto.user.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);



}