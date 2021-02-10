package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.CharacterRepository;
import com.example.moviecharacter.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")

public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterRepository characterRepository;

    //returns all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

/*    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getAllCharactersInMovie(@PathVariable Long id){
        List<Character> characters = characterRepository.findAllCharactersInMovie(id);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(characters, status);
    }*/

    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getCharactersByMovie(@PathVariable Long id){
        List<Character> characters;
        if (movieRepository.existsById(id)) {
            characters = new ArrayList<>(movieRepository.getOne(id).getCharacters());
            if (characters.size() > 0) {
                return new ResponseEntity<>(characters, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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

