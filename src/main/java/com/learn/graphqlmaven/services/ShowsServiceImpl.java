package com.learn.graphqlmaven.services;

import java.util.List;

import com.learn.graphqlmaven.models.entities.Review;
import com.learn.graphqlmaven.models.entities.Show;
import com.learn.graphqlmaven.repositories.ReviewRepository;
import com.learn.graphqlmaven.repositories.ShowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowsServiceImpl implements ShowsService {

    @Autowired
    private ShowRepository showRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Override
    public List<Show> getShows() {
        return showRepo.findAll();
    }

    @Override
    public List<Review> getReviewsForShow(String showId) {
        return reviewRepo.findByShowId(showId);
    }

    @Override
    public List<Show> getShowForReviews(List<String> showId) {
        List<Show> shows = showRepo.findByIdIn(showId);
        return shows;
    }

    public Show insert(String title) {
        Show show = new Show();
        show.setTitle(title);
        return showRepo.insert(show);
    }
}
