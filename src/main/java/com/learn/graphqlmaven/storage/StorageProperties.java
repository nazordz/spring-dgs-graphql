package com.learn.graphqlmaven.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("storage")
@Data
public class StorageProperties {
    
    @Value("${spring.path.upload}")
    private String location;
}
