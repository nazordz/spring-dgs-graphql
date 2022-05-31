package com.learn.graphqlmaven.datafetchers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.acme.DgsConstants;
import com.learn.graphqlmaven.dataloaders.ShowDataLoader;
import com.learn.graphqlmaven.models.entities.Review;
import com.learn.graphqlmaven.models.entities.Show;
import com.learn.graphqlmaven.models.input.ShowInput;
import com.learn.graphqlmaven.services.ShowsServiceImpl;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class ShowsDatafetcher {

    @Autowired
    private ShowsServiceImpl showsService;
    
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Shows)
    public List<Show> shows() {
        return showsService.getShows();
    }

    @DgsData(parentType = DgsConstants.REVIEW.TYPE_NAME, field = DgsConstants.REVIEW.Show)
    public CompletableFuture<Show> show(DgsDataFetchingEnvironment dfe) {
        DataLoader<String, Show> dataLoader = dfe.getDataLoader(ShowDataLoader.class);
        Review review = dfe.getSource();
        return dataLoader.load(review.getShowId());
    }

    public Show addShow(@InputArgument("input") ShowInput input) {
        return showsService.insert(input.getTitle());
    }
}