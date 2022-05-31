package com.learn.graphqlmaven.repositories;

import java.util.List;

import com.learn.graphqlmaven.models.entities.Show;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<Show, String>{
    public List<Show> findByIdIn(List<String> ids);
}
