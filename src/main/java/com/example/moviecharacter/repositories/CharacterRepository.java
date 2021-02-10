package com.example.moviecharacter.repositories;

import com.example.moviecharacter.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(
            value = "SELECT * FROM character INNER JOIN movie_character ON character.id = movie_character.character_id WHERE movie_character.movie_id = :param",
            nativeQuery = true)
    List<Character> findAllCharactersInMovie(@Param("param") Long id);
}
