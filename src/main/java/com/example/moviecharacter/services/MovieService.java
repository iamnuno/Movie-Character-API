package com.example.moviecharacter.services;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Franchise;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.FranchiseRepository;
import com.example.moviecharacter.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;

/*
* MovieService class.
* Acts as an service class between movieRepository and MovieController.
* Separating business logic from controller logic.
 */

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private FranchiseRepository franchiseRepository;

    //returns all movies in the database as an list
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        List<Movie> characters = movieRepository.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    //returns a single movie. Takes movie id as param.
    public ResponseEntity<Movie> getMovieById(long id) {
        HttpStatus status;
        // check if the movie exists, if it does set http status as ok and return the movie
        if(movieRepository.existsById(id)){
            Movie movie = movieRepository.findById(id).get();
            status = HttpStatus.OK;
            return new ResponseEntity<>(movie, status);
            //if movie doesn't exist set HttpStatus as not found and return a null
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    //returns all characters in a given movie. Takes movie id as param.
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

    //adds a movie into the database. Returns the added movie and http status created.
    public ResponseEntity<Movie> addMovie(Movie movie) {
        movie = movieRepository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    //updates a movie in the database. Checks that request provides all necessary fields.
    //returns the updated movie object if request was valid. If it's not valid returns httpstatus bad request or no content.
    public ResponseEntity<Movie> updateMovie(Long id, Movie movieToUpdate) {
        if (movieRepository.existsById(id)) {
            if (id == movieToUpdate.getId()) {
                Movie movie = movieRepository.findById(id).get();

                if (movieToUpdate.getTitle() != null)
                    movie.setTitle(movie.getTitle());

                if (movieToUpdate.getCharacters() != null)
                    movie.setCharacters(movieToUpdate.getCharacters());

                if (movieToUpdate.getDirector() != null)
                    movie.setDirector(movieToUpdate.getDirector());

                if (movieToUpdate.getPicture() != null)
                    movie.setPicture(movieToUpdate.getPicture());

                if (movieToUpdate.getFranchise() != null)
                    movie.setFranchise(movieToUpdate.getFranchise());

                if (movieToUpdate.getGenre() != null)
                    movie.setGenre(movieToUpdate.getGenre());

                if (movieToUpdate.getReleaseYear() != 0)
                    movie.setReleaseYear(movieToUpdate.getReleaseYear());

                if (movieToUpdate.getTrailer() != null)
                    movie.setTrailer(movieToUpdate.getTrailer());

                movieRepository.save(movie);
                return new ResponseEntity<>(movie, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    //deletes movie from the database. Takes movie id as param. Returns httpstatus not found if movie isn't in the database.
    public ResponseEntity<Movie> deleteMovie(long id) {
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