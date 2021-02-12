package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.*;
import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    /**
     * Calls FranchiseService to get all Franchises.
     * @return           List of Franchise objects and a HTTP status.
     */
    @GetMapping()
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }

    /**
     * Calls FranchiseService to get a single Franchise from the database.
     * @param id         Id of the Franchise.
     * @return           Franchise object and a HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable Long id) {
        return franchiseService.getFranchise(id);
    }

    /**
     * Calls FranchiseService to add a Franchise to the database.
     * @param franchise  Franchise object to be added.
     * @return           A Franchise entity and a HTTP status.
     */
    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        return franchiseService.addFranchise(franchise);
    }

    /**
     * Calls FranchiseService to update a Franchise in the database.
     * @param id         Id of the Franchise to be updated.
     * @param franchise  Franchise object to be updated.
     * @return           A Franchise response entity and a HTTP status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchise) {
        return franchiseService.updateFranchise(id, franchise);
    }

    /**
     * Calls FranchiseService to delete a Franchise from the database.
     * @param id         Id of the Franchise to be deleted.
     * @return           A Franchise response entity and a HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Long id) {
        return franchiseService.deleteFranchise(id);
    }

    /**
     * Calls FranchiseService to get all movies in a given Franchise from the database.
     * @param id         Id of the Franchise.
     * @return           List of all movies in the Franchise and a HTTP status.
     */
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<Movie>> getMoviesInFranchise(@PathVariable Long id) {
        return franchiseService.getMoviesInFranchise(id);
    }

    /**
     * Calls FranchiseService to get all characters in all movies in a Franchise from the database.
     * @param id         Id of the Franchise
     * @return           List of all characters in a Franchise and a HTTP status.
     */
    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getCharactersInFranchise(@PathVariable Long id) {
        return franchiseService.getCharactersInFranchise(id);
    }
}