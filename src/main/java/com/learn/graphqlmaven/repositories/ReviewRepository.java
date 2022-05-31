package com.learn.graphqlmaven.repositories;

import java.util.List;

import com.learn.graphqlmaven.models.entities.Review;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String>{
    public List<Review> findByShowId(String showId);

    public List<Review> findByShowIdIn(List<String> ids);
}
