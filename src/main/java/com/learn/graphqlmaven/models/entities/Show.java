package com.learn.graphqlmaven.models.entities;

import java.util.List;

import com.acme.types.IShow;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Document("shows")
@Data
public class Show implements IShow{
    @Id
    private String id;
    private String title;

    @DocumentReference(collection = "reviews")
    private List<Review> reviews;
}
