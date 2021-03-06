package com.example.moviecharacter.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class for Movie Objects
 */

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "genres")
    private String genre;
    @Column(name = "release_year")
    private int releaseYear;
    @Column(name = "director")
    private String director;
    @Column(name = "picture")
    private String picture;
    @Column(name = "trailer")
    private String trailer;

    @ManyToMany
    @JoinTable(
            name = "movie_character",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    private List<Character> characters;


    @JsonGetter("characters")
    public List<String> charactersGetter() {
        if (characters != null)
            return characters.stream().map(character -> "/api/v1/characters/" + character.getId()).collect(Collectors.toList());
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonGetter("franchise")
    public String franchiseGetter() {
        if (franchise != null)
            return "/api/v1/franchises/" + franchise.getId();
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }
}
