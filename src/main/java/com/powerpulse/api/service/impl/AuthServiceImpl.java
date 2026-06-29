package com.powerpulse.api.service.impl;
import com.powerpulse.api.dto.AuthResponse;
import com.powerpulse.api.dto.LoginRequest;
import com.powerpulse.api.dto.RegisterRequest;
import com.powerpulse.api.entity.User;
import com.powerpulse.api.repository.UserRepository;
import com.powerpulse.api.security.JwtService;
import com.powerpulse.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }
        User user = User.builder().fullName(request.getFullName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role("USER").build();
        userRepository.save(user);
        return "User registered successfully";
    }
    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}