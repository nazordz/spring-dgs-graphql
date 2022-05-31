package com.learn.graphqlmaven.models.entities;

import com.acme.types.IReview;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Document("reviews")
@Data
public class Review implements IReview {
    @Id
    private String id;
    private String showId;
    private Integer starRating;
    @DocumentReference(collection = "shows")
    private Show show;
}
