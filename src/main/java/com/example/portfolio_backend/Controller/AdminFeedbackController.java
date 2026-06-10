package com.example.portfolio_backend.Controller;

import com.example.portfolio_backend.Entity.Feedback;
import com.example.portfolio_backend.Service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/feedback")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminFeedbackController {

    private final FeedbackService feedbackService;

    public AdminFeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllReviews();
    }

    @GetMapping("/pending")
    public List<Feedback> getPendingFeedback() {
        return feedbackService.getPendingReviews();
    }

    @PutMapping("/{id}/approve")
    public Feedback approveFeedback(@PathVariable Long id) {
        return feedbackService.approveReview(id);
    }

    @PutMapping("/{id}/reject")
    public Feedback rejectFeedback(@PathVariable Long id) {
        return feedbackService.rejectReview(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteReview(id);
    }
}
