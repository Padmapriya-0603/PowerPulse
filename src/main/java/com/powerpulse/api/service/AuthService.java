package com.powerpulse.api.service;
import com.powerpulse.api.dto.AuthResponse;
import com.powerpulse.api.dto.LoginRequest;
import com.powerpulse.api.dto.RegisterRequest;
public interface AuthService {
    String register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}