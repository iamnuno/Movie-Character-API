package com.example.moviecharacter.repositories;

import com.example.moviecharacter.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository <Movie, Long> {
}