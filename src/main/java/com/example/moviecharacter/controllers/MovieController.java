package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.CharacterRepository;
import com.example.moviecharacter.repositories.MovieProjection;
import com.example.moviecharacter.repositories.MovieRepository;
import com.example.moviecharacter.services.MovieService;
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
    @Autowired
    private MovieService movieService;


    //returns all movies
    @GetMapping
    public ResponseEntity<List<MovieProjection>> getAllMovies(){
        return movieService.getAllMovies();
    }

/*    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getAllCharactersInMovie(@PathVariable Long id){
        List<Character> characters = characterRepository.findAllCharactersInMovie(id);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(characters, status);
    }*/

    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getCharactersByMovie(@PathVariable Long id){
        return movieService.getCharactersByMovie(id);
    }

    //returns a movie by id
    @GetMapping("/{id}")
    public ResponseEntity<MovieProjection> getMovie(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    //adding a movie
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    //updating a movie
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        return movieService.updateMovie(id, movie);
    }

    //deleting a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> updateCharacter(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }
}


