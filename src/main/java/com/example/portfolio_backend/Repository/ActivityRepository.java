package com.example.portfolio_backend.Repository;

import com.example.portfolio_backend.Entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    List<Activity> findAllByOrderByIdDesc();
}
