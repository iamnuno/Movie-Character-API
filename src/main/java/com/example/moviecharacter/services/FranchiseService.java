package com.example.moviecharacter.services;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Franchise;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    //get all franchises
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises,status);
    }

    //get a franchise
    public ResponseEntity<Franchise> getFranchise(@PathVariable Long id){
        Franchise returnFranchise = new Franchise();
        HttpStatus status;
        if(franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnFranchise = franchiseRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnFranchise, status);
    }


    //adding a franchise
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise){
        Franchise returnFranchise = franchiseRepository.save(franchise);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnFranchise, status);
    }

    //updating a Franchise
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchiseUpdate){
        Franchise returnFranchise = new Franchise();
        HttpStatus status;
        if (franchiseRepository.existsById(id)) {
            if (id == franchiseUpdate.getId()) {
                Franchise franchise = franchiseRepository.findById(id).get();

                if (franchiseUpdate.getName() != null)
                    franchise.setName(franchiseUpdate.getName());

                if (franchiseUpdate.getDescription() != null)
                    franchise.setDescription(franchiseUpdate.getDescription());

                if (franchiseUpdate.getMovies() != null)
                    franchise.setMovies(franchiseUpdate.getMovies());

                franchiseRepository.save(franchise);
                return new ResponseEntity<>(franchise, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    //deleting a franchise
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Long id) {
        HttpStatus status;
        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            franchiseRepository.deleteById(id);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
    }

    //getting all movies from franchise
    public ResponseEntity<List<Movie>> getMoviesInFranchise(@PathVariable Long id){
        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.findById(id).get();
            List<Movie> movies = franchise.getMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<List<Character>> getCharactersInFranchise(@PathVariable long id) {
        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.findById(id).get();
            List<Movie> movies = franchise.getMovies();
            List<Character> characters = new ArrayList<>();
            for (Movie movie : movies) {
                List<Character> charactersMovie = movie.getCharacters();
                for (Character character : charactersMovie) {
                    if (!characters.contains(character)) {
                        characters.add(character);
                    }
                }
            }
            return new ResponseEntity<>(characters, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
