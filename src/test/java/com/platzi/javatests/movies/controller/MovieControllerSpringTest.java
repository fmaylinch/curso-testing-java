package com.platzi.javatests.movies.controller;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import com.platzi.javatests.movies.service.MovieService;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerSpringTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
    
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
    public void getMovies() {

        ResponseEntity<Collection<Movie>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<Movie>>() {
                }
        );

        Collection<Movie> movies = response.getBody();

        assertThat( movies, is(sampleMovies) );
    }

    @Test
    public void getMoviesJson() throws Exception {

        String json = restTemplate.getForObject(
                "http://localhost:" + port + "/api/movies", String.class);

        String expectedJson = IOUtils.resourceToString(
                "/json/movies.json", StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expectedJson, json, true);
    }
}