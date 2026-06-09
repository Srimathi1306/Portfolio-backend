package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.Review;
import com.example.portfolio_backend.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository){
        this.repository = repository;
    }

    public List<Review> getAllReviews(){
        return repository.findAllByOrderByIdDesc();
    }

    public Review addReview(Review review){
        return repository.save(review);
    }

    public Review updateReview(Long id, Review updated){
        Review review = repository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        review.setReviewer(updated.getReviewer());
        review.setComment(updated.getComment());
        review.setRole(updated.getRole());
        return repository.save(review);
    }

    public void deleteReview(Long id){
        repository.deleteById(id);
    }

}