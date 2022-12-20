package com.systementor.movies.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.systementor.movies.model.Movie;
import com.systementor.movies.model.MovieRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

//@SpringBootTest
@WebMvcTest(ApiController.class)
public class ApiControllerTest {
    @MockBean
    private MovieRepository movieRepository;

    @Autowired
	private MockMvc mvc;

    static int n = 0;

    private Movie generateMovie(){
        n++;
        var m = new Movie();
        m.setId(n);
        m.setDirector("director" + Integer.toString(n));
        m.setGenre(n);
        m.setYear(2000+n);
        m.setTitle("title" + Integer.toString(n));
        return m;
    }

    @Test
    void single_movie() throws Exception{
        //ARRANGE
        Movie movie = generateMovie();
        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));

        //ACT + ASSERT
        this.mvc.perform(get("/movies/" + movie.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id",is(movie.getId())))
            .andExpect(jsonPath("$.director",is(movie.getDirector())))
            .andExpect(jsonPath("$.title",is(movie.getTitle())))
            .andDo(print());

    }

    @Test
    void testMovies() throws Exception{
        ArrayList<Movie> allMovies = new ArrayList<Movie>(
            Arrays.asList(generateMovie(),
                generateMovie(),
                generateMovie(),
                generateMovie())
        );
        when(movieRepository.findAll()).thenReturn(allMovies);
        this.mvc.perform(get("/movies"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(allMovies.size()))
            .andDo(print());
    }
}
