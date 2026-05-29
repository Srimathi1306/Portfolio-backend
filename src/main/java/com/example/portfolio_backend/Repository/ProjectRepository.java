package com.example.portfolio_backend.Repository;

import com.example.portfolio_backend.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
