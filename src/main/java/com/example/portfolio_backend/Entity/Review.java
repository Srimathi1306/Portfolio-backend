package com.example.portfolio_backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;

    @NotBlank
    private String reviewer;

    @Column(length = 1000)
    private String comment;

    private String role;
}
