package com.platzi.javatests.movies.service;

import com.platzi.javatests.movies.data.MovieRepository;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findAll() {

        return movieRepository.findAll();
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByLength(int length) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= length).collect(Collectors.toList());
    }
}
