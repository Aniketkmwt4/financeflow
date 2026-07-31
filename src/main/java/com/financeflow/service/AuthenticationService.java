package com.financeflow.service;

import com.financeflow.dto.auth.LoginRequest;
import com.financeflow.dto.auth.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

}