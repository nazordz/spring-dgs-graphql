package com.learn.graphqlmaven.models.entities;

import com.acme.types.IStock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock implements IStock {
    private String name;
    private Double price;
}
