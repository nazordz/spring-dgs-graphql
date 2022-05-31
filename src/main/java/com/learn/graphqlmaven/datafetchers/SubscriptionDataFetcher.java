package com.learn.graphqlmaven.datafetchers;

import java.time.Duration;

import com.learn.graphqlmaven.models.entities.Stock;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsSubscription;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;

@DgsComponent
public class SubscriptionDataFetcher {
    @DgsSubscription
    public Publisher<Stock> stocks() {
        return Flux.interval(Duration.ofSeconds(0), Duration.ofSeconds(1)).map(t -> new Stock("NFLX", 500.0 + t));
    }
}
