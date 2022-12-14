package com.systementor.movies.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.systementor.movies.model.Genre;
import com.systementor.movies.model.GenreRepository;
import com.systementor.movies.model.Movie;
import com.systementor.movies.model.MovieRepository;
import com.systementor.viewmodel.HomeMovie;

import org.springframework. ui.Model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;


    @Autowired
    public HomeController(MovieRepository movieRepository, GenreRepository genreRepository) {
        super();
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }


    @GetMapping(path="/")
    String empty(Model model)
    {
        var movieViewModels = new ArrayList<HomeMovie>();
        var allGenres = new ArrayList<Genre>();
        for(Genre r : genreRepository.findAll())
            allGenres.add(r);

        for(Movie m : movieRepository.findAll()){
            movieViewModels.add(new HomeMovie(m.getTitle(), m.getDirector(), m.getYear(), getGenreName(m.getGenre(), allGenres)));
        }
        model.addAttribute("Movies", movieViewModels);
        return "home";
    }


    private String getGenreName(Integer genre, ArrayList<Genre> allGenres) {
        for(Genre acc: allGenres){
            if(acc.getId().equals(genre)) return acc.getName();
        }
        return null;
    }
    
}
