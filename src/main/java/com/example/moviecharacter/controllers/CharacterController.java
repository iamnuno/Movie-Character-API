package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/characters")
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public ResponseEntity<List<Character>> getCharacters() {
        List<Character> characters = characterRepository.findAll();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterbyId(@PathVariable Long id) {
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

    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        character = characterRepository.save(character);
        return new ResponseEntity<>(character, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character characterUpdate) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id) {
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
