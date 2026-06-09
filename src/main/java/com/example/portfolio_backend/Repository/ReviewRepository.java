package com.example.portfolio_backend.Repository;

import com.example.portfolio_backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByOrderByIdDesc();
}
