package com.platzi.javatests.movies;

import com.platzi.javatests.movies.data.DataSourceUtil;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.service.MovieService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class MoviesApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MoviesApp.class, args);
		MovieService movieService = context.getBean(MovieService.class);
		System.out.println( movieService.findMoviesByGenre(Genre.ACTION) );
	}

	@Bean
	public DataSource getDataSource() {
		return DataSourceUtil.getDataSourceInPath();
	}
}

