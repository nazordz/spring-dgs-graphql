package com.learn.graphqlmaven.dataloaders;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.learn.graphqlmaven.models.entities.Show;
import com.learn.graphqlmaven.services.ShowsServiceImpl;
import com.netflix.graphql.dgs.DgsDataLoader;

import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nazor
 * I use this to show one data
 */
@DgsDataLoader(name = "show")
public class ShowDataLoader implements BatchLoader<String, Show>{

    @Autowired
    private ShowsServiceImpl showsService;

    @Override
    public CompletionStage<List<Show>> load(List<String> keys) {
        return CompletableFuture.supplyAsync(() -> showsService.getShowForReviews(keys));
    }
}
