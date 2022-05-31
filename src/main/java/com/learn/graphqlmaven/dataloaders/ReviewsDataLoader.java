package com.learn.graphqlmaven.dataloaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.learn.graphqlmaven.models.entities.Review;
import com.learn.graphqlmaven.services.ReviewsService;
import com.netflix.graphql.dgs.DgsDataLoader;

import org.dataloader.MappedBatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nazor
 * I use this to show many data
 */
@DgsDataLoader(name = "reviews")
public class ReviewsDataLoader implements MappedBatchLoader<String, List<Review>> {
    
    @Autowired
    private ReviewsService reviewsService;

    @Override
    public CompletionStage<Map<String, List<Review>>> load(Set<String> keys) {
        return CompletableFuture.supplyAsync(() -> reviewsService.reviewsForShows(new ArrayList<>(keys)));
    }

}
