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

/**
 * FranchiseService class acts as a service class between FranchiseRepository and FranchiseController.
 * Separating business logic from controller logic.
 */

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    /**
     * Calls FranchiseRepository to get all franchises from the database.
     * @return                 List of Franchise objects and a HTTP status.
     */
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises,status);
    }

    /**
     * Calls FranchiseRepository to get a single Franchise from the database.
     * @param id               Id of the Franchise.
     * @return                 Franchise object and a HTTP status.
     */
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


    /**
     * Calls FranchiseRepository to add a Franchise to the database.
     * @param franchise        Franchise object to be added.
     * @return                 A Franchise entity and a HTTP status.
     */
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise){
        HttpStatus status;
        if(franchiseRepository.existsById(franchise.getId())) {
            Franchise returnFranchise = franchiseRepository.save(franchise);
            status = HttpStatus.CREATED;
            return new ResponseEntity<>(returnFranchise, status);
        } else {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(null, status);
        }
    }

    /**
     * Calls FranchiseRepository to update a Franchise in the database.
     * @param id               Id of the Franchise to be updated.
     * @param franchiseUpdate  Franchise object to be updated.
     * @return                 A Franchise response entity and a HTTP status.
     */
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

    /**
     * Calls FranchiseRepository to delete a Franchise from the database.
     * @param id               Id of the Franchise to be deleted.
     * @return                 A Franchise response entity and a HTTP status.
     */
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

    /**
     * Calls FranchiseRepository to get all movies in a given Franchise from the database.
     * @param id               Id of the Franchise.
     * @return                 List of all movies in the Franchise and a HTTP status.
     */
    public ResponseEntity<List<Movie>> getMoviesInFranchise(@PathVariable Long id){
        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.findById(id).get();
            List<Movie> movies = franchise.getMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    /**
     * Calls FranchiseRepository to get all characters in all movies in a Franchise from the database.
     * @param id               Id of the Franchise
     * @return                 List of all characters in a Franchise and a HTTP status.
     */
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
