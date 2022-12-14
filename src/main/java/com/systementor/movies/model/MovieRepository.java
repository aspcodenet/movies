package com.systementor.movies.model;

import org.springframework.data.repository.CrudRepository;
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    
}
