package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Franchise;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @GetMapping()
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises,status);
    }

    @GetMapping("/{id}")
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

    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise){
        Franchise returnFranchise = franchiseRepository.save(franchise);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnFranchise, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchise){
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if(!id.equals(franchise.getId())){
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnFranchise, status);
        }
        returnFranchise = franchiseRepository.save(franchise);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnFranchise, status);
    }

    //deleting a franchise
    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Long id) {
        HttpStatus status;
        if (franchiseRepository.existsById(id)){
            status = HttpStatus.OK;
            franchiseRepository.deleteById(id);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<Movie>> getMoviesInFranchise(@PathVariable long id){
        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.findById(id).get();
            List<Movie> movies = franchise.getMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/characters")
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