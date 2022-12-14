package com.systementor.movies;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.systementor.movies.model.Genre;
import com.systementor.movies.model.GenreRepository;
import com.systementor.movies.model.Movie;
import com.systementor.movies.model.MovieRepository;

@Component
public class SeedData implements CommandLineRunner {
    
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    @Autowired
    public SeedData(MovieRepository movieRepository, GenreRepository genreRepository) {
        super();
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {
        seedGenres();
        seedMovies();
    }

    private void seedMovies() {
        var allGenres = new ArrayList<Genre>();
        for(Genre r : genreRepository.findAll())
            allGenres.add(r);

        var allMovies = new ArrayList<Movie>();
        for(Movie r : movieRepository.findAll())
            allMovies.add(r);
    
        addMovieIsNotExisting("The Matrix","The Wachowskis",1999, "Action", allMovies, allGenres );    
        addMovieIsNotExisting("Conan The Barbarian","John Millius",1982, "Action", allMovies, allGenres );    
        addMovieIsNotExisting("Alien","Ridley Scott",1979, "Sci-fi", allMovies, allGenres );    

    }

    private void addMovieIsNotExisting(String title, String director, int year, String genreName, ArrayList<Movie> allMovies,
            ArrayList<Genre> allGenres) {

            for(Movie acc: allMovies){
                if(acc.getTitle().equals(title)) return;
            }

            var movie = new Movie();
            movie.setTitle(title);
            movie.setGenre(GetGenreId(genreName,allGenres));
            movie.setDirector(director);
            movie.setYear(year);
            movieRepository.save(movie);
    }

    private Integer GetGenreId(String genreName, ArrayList<Genre> allGenres) {
        for(Genre acc: allGenres){
            if(acc.getName().equals(genreName)) return acc.getId();
        }
        return null;
    }

    private void seedGenres() {
        var all = new ArrayList<Genre>();
        for(Genre r : genreRepository.findAll())
            all.add(r);
        addGenreIsNotExisting(all,"Action");
        addGenreIsNotExisting(all,"Sci-fi");
    }


    private void addGenreIsNotExisting(ArrayList<Genre> all, String name) {
        for(Genre acc: all){
            if(acc.getName().equals(name)) return;
        }
        var n = new Genre();
        n.setName(name);
        genreRepository.save(n);
    }

}
