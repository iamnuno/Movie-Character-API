package com.example.moviecharacter.repositories;

import com.example.moviecharacter.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * MovieRepository interface, extends JpaRepository
 * Provides CRUD functionality for Movie objects in the database
 */
@Repository
public interface MovieRepository extends JpaRepository <Movie, Long> {
}