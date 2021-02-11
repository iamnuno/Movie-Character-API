package com.example.moviecharacter.services;

import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<List<Movie>> getCharacters() {
        List<Movie> characters = movieRepository.findAll();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    public ResponseEntity<Movie> getMovieById(long id) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)){
            status = HttpStatus.OK;
            returnMovie = movieRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(null, status);
    }

    public ResponseEntity<Movie> addMovie(Movie movie) {
        movie = movieRepository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

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

                if (movieToUpdate.getFranchise() != null) {
                    movie.setFranchise(movieToUpdate.getFranchise());
                }
                if (movieToUpdate.getGenre() != null) {
                    movie.setGenre(movieToUpdate.getGenre());
                }
                if (movieToUpdate.getReleaseYear() != 0) {
                    movie.setReleaseYear(movieToUpdate.getReleaseYear());
                }
                if (movieToUpdate.getTrailer() != null) {
                    movie.setTrailer(movieToUpdate.getTrailer());
                }

                return new ResponseEntity<>(movie, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

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
