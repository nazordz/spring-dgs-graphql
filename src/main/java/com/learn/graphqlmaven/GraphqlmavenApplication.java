package com.learn.graphqlmaven;

import com.learn.graphqlmaven.storage.StorageProperties;
import com.learn.graphqlmaven.storage.FileSystemStorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GraphqlmavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlmavenApplication.class, args);
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
