package com.powerpulse.api.service;
import com.powerpulse.api.entity.User;
import com.powerpulse.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()).password(user.getPassword())
                .roles(user.getRole()).build();
    }
}