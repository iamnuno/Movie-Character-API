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

/**
 * MovieController provides mapping for API's movie path.
 */

public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * GET mapping for all movies in the database.
     * @return      ResponseEntity containing list of Movie objects and HTTP Status.
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return movieService.getAllMovies();
    }

    /**
     * GET mapping for all characters in a given movie.
     * @param id    id of the movie as path variable.
     * @return      ResponseEntity containing list of Movie objects and HTTP Status.
     */
    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getCharactersByMovie(@PathVariable Long id){ return movieService.getCharactersByMovie(id); }

    /**
     * GET mapping for a given movie.
     * @param id    id of the movie as path variable.
     * @return      ResponseEntity containing a Movie object and HTTP Status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id){ return movieService.getMovieById(id);  }

    /**
     * POST mapping for a adding a movie.
     * @param movie    Movie object in request body
     * @return      ResponseEntity containing the added Movie object and HTTP Status.
     */
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    /**
     * PUT mapping for updating a movie.
     * @param id    id of the Movie as path variable.
     * @param movie    Movie object in request body.
     * @return      ResponseEntity containing the updated Movie object and HTTP Status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){ return movieService.updateMovie(id, movie); }

    /**
     * DELETE mapping for deleting a movie.
     * @param id    id of the Movie as path variable.
     * @return      ResponseEntity containing the updated Movie object and HTTP Status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> updateCharacter(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }
}

