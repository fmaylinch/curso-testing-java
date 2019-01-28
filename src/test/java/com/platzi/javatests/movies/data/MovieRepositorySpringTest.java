package com.platzi.javatests.movies.data;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@JdbcTest
public class MovieRepositorySpringTest {

    private MovieRepositoryJdbc movieRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        movieRepository = new MovieRepositoryJdbc(dataSource);
    }

    @Test
    public void load_all_movies() throws SQLException {

        Collection<Movie> movies = movieRepository.findAll();

        assertThat( movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )) );
    }

    @Test
    public void load_movie_by_id() {

        Movie movie = movieRepository.findById(2);

        assertThat( movie, is(new Movie(2, "Memento", 113, Genre.THRILLER)) );
    }

    @Test
    public void insert_a_movie() {

        Movie movie = new Movie("Super 8", 112, Genre.THRILLER);

        movieRepository.saveOrUpdate(movie);

        Movie movieFromDb = movieRepository.findById(4);

        assertThat( movieFromDb, is(new Movie(4, "Super 8", 112, Genre.THRILLER)) );
    }

    @After
    public void tearDown() throws Exception {

        // Remove H2 files -- https://stackoverflow.com/a/51809831/1121497
        jdbcTemplate.execute("drop all objects delete files"); // "shutdown" is also enough for mem db
    }
}