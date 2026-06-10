package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.Activity;
import com.example.portfolio_backend.Entity.FeedbackStatus;
import com.example.portfolio_backend.Entity.Project;
import com.example.portfolio_backend.Entity.Feedback;
import com.example.portfolio_backend.Repository.ActivityRepository;
import com.example.portfolio_backend.Repository.ProjectRepository;
import com.example.portfolio_backend.Repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;
    private final ActivityRepository activityRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            ProjectRepository projectRepository,
            ActivityRepository activityRepository
    ) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
        this.activityRepository = activityRepository;
    }

    public List<Feedback> getAllReviews() {
        return feedbackRepository.findAllByOrderByIdDesc();
    }

    public List<Feedback> getPendingReviews() {
        return feedbackRepository.findByStatusOrderByIdDesc(FeedbackStatus.PENDING);
    }

    public Feedback addProjectFeedback(Long projectId, Feedback feedback) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        feedback.setProject(project);
        feedback.setActivity(null);
        feedback.setStatus(FeedbackStatus.PENDING);

        return feedbackRepository.save(feedback);
    }

    public Feedback addActivityFeedback(Long activityId, Feedback feedback) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        feedback.setActivity(activity);
        feedback.setProject(null);
        feedback.setStatus(FeedbackStatus.PENDING);

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getApprovedProjectFeedback(Long projectId) {
        return feedbackRepository.findByProjectIdAndStatusOrderByIdDesc(
                projectId,
                FeedbackStatus.APPROVED
        );
    }

    public List<Feedback> getApprovedActivityFeedback(Long activityId) {
        return feedbackRepository.findByActivityIdAndStatusOrderByIdDesc(
                activityId,
                FeedbackStatus.APPROVED
        );
    }

    public Feedback approveReview(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        feedback.setStatus(FeedbackStatus.APPROVED);

        return feedbackRepository.save(feedback);
    }

    public Feedback rejectReview(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        feedback.setStatus(FeedbackStatus.REJECTED);

        return feedbackRepository.save(feedback);
    }

    public void deleteReview(Long id) {
        feedbackRepository.deleteById(id);
    }
}