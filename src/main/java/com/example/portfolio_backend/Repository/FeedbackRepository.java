package com.example.portfolio_backend.Repository;

import com.example.portfolio_backend.Entity.FeedbackStatus;
import com.example.portfolio_backend.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findAllByOrderByIdDesc();

    List<Feedback> findByStatusOrderByIdDesc(FeedbackStatus status);

    List<Feedback> findByProjectIdAndStatusOrderByIdDesc(Long projectId, FeedbackStatus status);

    List<Feedback> findByActivityIdAndStatusOrderByIdDesc(Long activityId, FeedbackStatus status);
}
