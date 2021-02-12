package com.example.moviecharacter.services;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Franchise;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.CharacterRepository;
import com.example.moviecharacter.repositories.FranchiseRepository;
import com.example.moviecharacter.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * MovieService class acts as an service class between MovieRepository and MovieController
 * Separating business logic from controller logic
 */
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private FranchiseRepository franchiseRepository;

    /**
     * Calls MovieRepository to get all movies from the database
     * @return      List of Movie objects and HTTP Status
     */
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    /**
     * Calls MovieRepository to get a single movie from the database
     * @param id    Private key of the Movie
     * @return      Movie object and HTTP Status
     */
    public ResponseEntity<Movie> getMovieById(Long id) {
        if(movieRepository.existsById(id)){
            Movie movie = movieRepository.findById(id).get();
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Calls MovieRepository to get all Characters in a given movie from the database
     * @param id    Private key of the Movie
     * @return      List of Characters and HTTP Status
     */
    public ResponseEntity<List<Character>> getCharactersByMovie(Long id){
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

    /**
     * Calls MovieRepository to add a movie in to the database
     * @param movie    Movie object to be added
     * @return      A movie response entity and HTTP Status
     */
    public ResponseEntity<Movie> addMovie(Movie movie) {
        if (!movieRepository.existsById(movie.getId()))
            return new ResponseEntity<>(movieRepository.save(movie), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Calls MovieRepository to update a movie in the database
     * @param movieToUpdate    Movie object to be updated
     * @param id    Id of the movie to be updated
     * @return      A movie response entity and HTTP Status
     */
    public ResponseEntity<Movie> updateMovie(Long id, Movie movieToUpdate) {
        if (movieRepository.existsById(id)) {
            if (id == movieToUpdate.getId()) {
                Movie movie = movieRepository.findById(id).get();

                if (movieToUpdate.getTitle() != null)
                    movie.setTitle(movieToUpdate.getTitle());

                if (movieToUpdate.getDirector() != null)
                    movie.setDirector(movieToUpdate.getDirector());

                if (movieToUpdate.getPicture() != null)
                    movie.setPicture(movieToUpdate.getPicture());

                if (movieToUpdate.getFranchise() != null){
                    Franchise franchise = movieToUpdate.getFranchise();
                    if (franchiseRepository.existsById(franchise.getId()))
                        movie.setFranchise(franchise);
                    else
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                if (movieToUpdate.getGenre() != null)
                    movie.setGenre(movieToUpdate.getGenre());

                if (movieToUpdate.getReleaseYear() != 0)
                    movie.setReleaseYear(movieToUpdate.getReleaseYear());

                if (movieToUpdate.getTrailer() != null)
                    movie.setTrailer(movieToUpdate.getTrailer());

                if (movieToUpdate.getCharacters() != null) {
                    List<Character> characters = movieToUpdate.getCharacters();
                    List<Character> newCharacters = new ArrayList<>();
                    for (Character character : characters) {
                        if (characterRepository.existsById(character.getId()))
                                newCharacters.add(character);
                            else
                                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                        }
                        movie.setCharacters(newCharacters);
                }
                movieRepository.save(movie);
                return new ResponseEntity<>(movie,HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    /**
     * Calls MovieRepository to delete a movie from the database
     * @param id    Id of the movie to be deleted
     * @return      A movie response entity and HTTP Status
     */
    public ResponseEntity<Movie> deleteMovie(Long id) {
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