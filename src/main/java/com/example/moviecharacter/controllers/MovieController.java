package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")

public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    //returns all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    //returns a movie by id
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id){
        Movie movie = new Movie();
        HttpStatus status;
        // check if the movie exists, if it does set http status as ok
        if(movieRepository.existsById(id)){
            status = HttpStatus.OK;
            movie = movieRepository.findById(id).get();
        //if movie doesn't exist set HttpStatus as not found
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movie, status);
    }

    //adding a movie
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        HttpStatus status = HttpStatus.CREATED;
        movie = movieRepository.save(movie);
        return new ResponseEntity<>(movie, status);
    }

    //updating a movie
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        Movie toReturn = new Movie();
        HttpStatus status;

        if(!id.equals(movie.getId())){
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(movie,status);
        }
        toReturn = movieRepository.save(movie);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(toReturn, status);
    }

    //deleting a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> updateCharacter(@PathVariable Long id) {
        HttpStatus status;
        if (movieRepository.existsById(id)){
            status = HttpStatus.OK;
            movieRepository.deleteById(id);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
    }
}
