package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.AdminUser;
import com.example.portfolio_backend.Repository.AdminUserRepository;
import com.example.portfolio_backend.dto.LoginRequest;
import com.example.portfolio_backend.dto.LoginResponse;
import com.example.portfolio_backend.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            AdminUserRepository adminUserRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        AdminUser adminUser = adminUserRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        boolean passwordMatches = passwordEncoder.matches(
                loginRequest.getPassword(),
                adminUser.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(
                adminUser.getUsername(),
                adminUser.getRole()
        );

        return new LoginResponse(
                token,
                adminUser.getUsername(),
                adminUser.getRole()
        );
    }
}