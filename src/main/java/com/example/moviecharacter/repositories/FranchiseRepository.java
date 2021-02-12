package com.example.moviecharacter.repositories;

import com.example.moviecharacter.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FranchiseRepository interface makes it possible to implement JpaRepository methods.
 */
@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
