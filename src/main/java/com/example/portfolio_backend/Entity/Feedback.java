package com.example.portfolio_backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String reviewer;

    private String role;

    @Column(length = 1000)
    @NotBlank
    private String comment;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = FeedbackStatus.PENDING;
        }
    }
}
