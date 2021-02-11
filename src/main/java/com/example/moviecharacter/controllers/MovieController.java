package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")

/*
* MovieController class. Provides mapping for the API's movie endpoints
 */

public class MovieController {

    @Autowired
    private MovieService movieService;

    //mapping for getting all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return movieService.getAllMovies();
    }

    //mapping for getting all characters in a given movie
    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getCharactersByMovie(@PathVariable Long id){ return movieService.getCharactersByMovie(id); }

    //mapping for getting a movie by id
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id){ return movieService.getMovieById(id);  }

    //mapping for adding a movie into database
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    //umapping for updating a move in the database
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){ return movieService.updateMovie(id, movie); }

    //mapping for deleting a movie from the database
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> updateCharacter(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }
}

