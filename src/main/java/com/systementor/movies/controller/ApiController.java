package com.systementor.movies.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systementor.movies.model.Movie;
import com.systementor.movies.model.MovieRepository;

@RestController
public class ApiController {


    @Autowired
    MovieRepository movieRepository;


    @GetMapping("/movies")        
    ResponseEntity<Iterable<Movie>> movies(){
        //hämta 
        //mappa
        //beräkna
        // returnerar INTE entiteten utan  en specifik modell/viewmodel
        // id -> id2
        return new ResponseEntity<Iterable<Movie>>(movieRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/movies/{id}")        
    ResponseEntity<Movie> movie(@PathVariable("id") int id){
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent())
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
