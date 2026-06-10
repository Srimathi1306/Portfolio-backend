package com.example.portfolio_backend.Controller;

import com.example.portfolio_backend.Entity.Feedback;
import com.example.portfolio_backend.Service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/projects/{projectId}/feedback")
    public Feedback addProjectFeedback(
            @PathVariable Long projectId,
            @RequestBody Feedback feedback
    ) {
        return feedbackService.addProjectFeedback(projectId, feedback);
    }

    @GetMapping("/projects/{projectId}/feedback/approved")
    public List<Feedback> getApprovedProjectFeedback(@PathVariable Long projectId) {
        return feedbackService.getApprovedProjectFeedback(projectId);
    }

    @PostMapping("/activities/{activityId}/feedback")
    public Feedback addActivityFeedback(
            @PathVariable Long activityId,
            @RequestBody Feedback feedback
    ) {
        return feedbackService.addActivityFeedback(activityId, feedback);
    }

    @GetMapping("/activities/{activityId}/feedback/approved")
    public List<Feedback> getApprovedActivityFeedback(@PathVariable Long activityId) {
        return feedbackService.getApprovedActivityFeedback(activityId);
    }
}