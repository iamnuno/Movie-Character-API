package com.example.moviecharacter.services;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

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
        character = characterRepository.save(character);
        return new ResponseEntity<>(character, HttpStatus.CREATED);
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
                    character.setMovies(characterUpdate.getMovies());
                }

                characterRepository.save(character);
                return new ResponseEntity<>(character, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
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
