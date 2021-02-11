package com.example.moviecharacter.repositories;

import com.example.moviecharacter.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Long> {
    @Query(
            value = "SELECT * FROM movie WHERE franchise_id = :param",
            nativeQuery = true)
    List<Movie> findAllMoviesInFranchise(@Param("param") Long id);
}