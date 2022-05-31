package com.learn.graphqlmaven.services;

import java.util.List;

import com.learn.graphqlmaven.models.entities.Review;
import com.learn.graphqlmaven.models.entities.Show;

interface ShowsService {
    List<Show> getShows(); //Does not include reviews
    List<Review> getReviewsForShow(String showId);   
    List<Show> getShowForReviews(List<String> showId);
}
