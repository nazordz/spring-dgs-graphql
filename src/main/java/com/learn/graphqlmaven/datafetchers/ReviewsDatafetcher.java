package com.learn.graphqlmaven.datafetchers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.acme.DgsConstants;
import com.learn.graphqlmaven.dataloaders.ReviewsDataLoader;
import com.learn.graphqlmaven.models.entities.Review;
import com.learn.graphqlmaven.models.entities.Show;
import com.learn.graphqlmaven.services.ReviewsService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import graphql.schema.DataFetchingEnvironment;

@DgsComponent
public class ReviewsDatafetcher {
    
    @Autowired
    private ReviewsService reviewsService;
    
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Reviews)
    public List<Review> reviews() {
        return reviewsService.getReviews();
    }
    
    @DgsData(parentType = DgsConstants.SHOW.TYPE_NAME, field = DgsConstants.SHOW.Reviews)
    public CompletableFuture<List<Review>> reviewsForShows(DgsDataFetchingEnvironment dfe) {
        DataLoader<String, List<Review>> reviewsDataLoader = dfe.getDataLoader(ReviewsDataLoader.class);
        Show show = dfe.getSource();

        return reviewsDataLoader.load(show.getId());
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.AddReview)
    public Review addReview(DataFetchingEnvironment dfe) {
        String showId = dfe.getArgument("showId");
        Integer starRating = dfe.getArgument("starRating");

        Review review = new Review();
        review.setShowId(showId);
        review.setStarRating(starRating);

        return reviewsService.insert(review);
    }

}
