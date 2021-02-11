package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.*;
import com.example.moviecharacter.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    //getting all franchises
    @GetMapping()
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }

    //get a franchise
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable Long id) {
        return franchiseService.getFranchise(id);
    }

    //adding a franchise
    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        return franchiseService.addFranchise(franchise);
    }

    //updating a Franchise
    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchise) {
        return franchiseService.updateFranchise(id, franchise);
    }

    //deleting a franchise
    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Long id) {
        return franchiseService.deleteFranchise(id);
    }

    //getting all movies from franchise
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<Movie>> getMovies(@PathVariable Long id) {
        return franchiseService.getMovies(id);
    }
}