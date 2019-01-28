package com.platzi.javatests.movies;

import com.platzi.javatests.movies.data.DataSourceUtil;
import com.platzi.javatests.movies.data.MovieRepository;
import com.platzi.javatests.movies.data.MovieRepositoryJdbc;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.service.MovieService;

import javax.sql.DataSource;

public class MoviesAppNoSpring {

    public static void main(String[] args) {

        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        MovieRepository movieRepository = new MovieRepositoryJdbc(dataSource);
        MovieService movieService = new MovieService(movieRepository);

        System.out.println( movieService.findMoviesByGenre(Genre.ACTION) );
    }
}
