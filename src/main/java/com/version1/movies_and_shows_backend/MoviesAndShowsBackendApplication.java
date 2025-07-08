package com.version1.movies_and_shows_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MoviesAndShowsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesAndShowsBackendApplication.class, args);
	}

}
