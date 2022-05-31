package com.learn.graphqlmaven.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.learn.graphqlmaven.models.entities.Review;
import com.learn.graphqlmaven.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {

    @Autowired
    private ReviewRepository reviewRepo;
    
    public List<Review> getReviews() {
        return reviewRepo.findAll();
    }

    public Map<String, List<Review>> reviewsForShows(List<String> showIds) {
        Map<String, List<Review>> reviews = new ConcurrentHashMap<>();
        List<Review> findByShowIdIn = reviewRepo.findByShowIdIn(showIds);

        showIds.forEach(id -> {
            List<Review> listReview = findByShowIdIn
                .stream()
                .filter(re -> re.getShowId().equals(id))
                .toList();

            reviews.put(id, listReview);
        });

        return reviews;
    }

    public Review insert(Review review) {
        return reviewRepo.insert(review);
    }
}
