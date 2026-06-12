package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.AdminUser;
import com.example.portfolio_backend.Repository.AdminUserRepository;
import com.example.portfolio_backend.dto.LoginRequest;
import com.example.portfolio_backend.dto.LoginResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            AdminUserRepository adminUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
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

        return new LoginResponse(
                "Login successful",
                adminUser.getUsername(),
                adminUser.getRole()
        );
    }
}