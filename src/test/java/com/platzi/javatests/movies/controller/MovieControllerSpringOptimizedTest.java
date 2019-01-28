package com.platzi.javatests.movies.controller;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import com.platzi.javatests.movies.service.MovieService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerSpringOptimizedTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private MovieService movieService;

    private List<Movie> sampleMovies;

    @Before
    public void setUp() throws Exception {

        sampleMovies = Arrays.asList(
                new Movie(1, "Memento", 113, Genre.THRILLER),
                new Movie(2, "Super 8", 112, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        );
        Mockito.when(movieService.findAll()).thenReturn(
                sampleMovies
        );
    }

    @Test
    public void getMovies() throws Exception {

        String expectedJson = IOUtils.resourceToString(
                "/json/movies.json", StandardCharsets.UTF_8);

        mockMvc.perform(get("/api/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}