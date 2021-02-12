package com.example.moviecharacter.services;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.models.Movie;
import com.example.moviecharacter.repositories.CharacterRepository;
import com.example.moviecharacter.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class handling character related database repository interaction.
 */
@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<List<Character>> getCharacters() {
        List<Character> characters = characterRepository.findAll();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    public ResponseEntity<Character> getCharacterById(long id) {
        Character returnCharacter = new Character();
        HttpStatus status;

        if (characterRepository.existsById(id)){
            status = HttpStatus.OK;
            returnCharacter = characterRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(returnCharacter, status);
    }

    public ResponseEntity<Character> addCharacter(Character character) {
        if (!characterRepository.existsById(character.getId())) {
            character = characterRepository.save(character);
            return new ResponseEntity<>(character, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Character> updateCharacter(Long id, Character characterUpdate) {
        if (characterRepository.existsById(id)) {
            if (id == characterUpdate.getId()) {
                Character character = characterRepository.findById(id).get();

                if (characterUpdate.getFullName() != null)
                    character.setFullName(characterUpdate.getFullName());

                if (characterUpdate.getAlias() != null)
                    character.setAlias(characterUpdate.getAlias());

                if (characterUpdate.getGender() != null)
                    character.setGender(characterUpdate.getGender());

                if (characterUpdate.getPicture() != null)
                    character.setPicture(characterUpdate.getPicture());

                if (characterUpdate.getMovies() != null) {
                    // check if movies exist in database before setting them in character
                    List<Movie> movies = characterUpdate.getMovies();
                    List<Movie> newMovies = new ArrayList<>();
                    for (Movie movie : movies) {
                        if (movieRepository.existsById(movie.getId())) {
                            newMovies.add(movie);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                        }
                    }
                    character.setMovies(newMovies);
                }

                characterRepository.save(character);
                return new ResponseEntity<>(character, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Character> deleteCharacter(long id) {
        Character character = new Character();
        character.setId(id);
        HttpStatus status;

        if (characterRepository.existsById(id)){
            status = HttpStatus.OK;
            characterRepository.delete(character);
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(null, status);
    }
}
