package com.example.moviecharacter.controllers;

import com.example.moviecharacter.models.Character;
import com.example.moviecharacter.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller mappings for characters
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /**
     * Mapping for getting all characters.
     *
     * @return ResponseEntity object
     */
    @GetMapping
    public ResponseEntity<List<Character>> getCharacters() {
        return characterService.getCharacters();
    }

    /**
     * Mapping for getting a specific character by id.
     *
     * @return ResponseEntity object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    /**
     * Mapping for creating a new character.
     *
     * @return ResponseEntity object
     */
    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        return characterService.addCharacter(character);
    }

    /**
     * Mapping for updating a specific character by id.
     *
     * @return ResponseEntity object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character characterUpdate) {
        return characterService.updateCharacter(id, characterUpdate);
    }

    /**
     * Mapping for deleting a specific character by id.
     *
     * @return ResponseEntity object
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Character> deleteCharacter(@PathVariable Long id) {
        return characterService.deleteCharacter(id);
    }
}
