package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.Activity;
import com.example.portfolio_backend.Repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAllByOrderByIdDesc();
    }

    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        existingActivity.setTitle(updatedActivity.getTitle());
        existingActivity.setContent(updatedActivity.getContent());
        existingActivity.setDate(updatedActivity.getDate());

        return activityRepository.save(existingActivity);
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
