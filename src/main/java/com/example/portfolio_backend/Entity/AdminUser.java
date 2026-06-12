package com.example.portfolio_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}
