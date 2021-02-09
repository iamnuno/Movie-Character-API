package com.example.moviecharacter.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;
    @Column(name = "genres")
    private String genre;
    @Column(name = "release_year")
    private String releaseYear;
    @Column(name = "director")
    private String director;
    @Column(name = "picture")
    private String picture;
    @Column(name = "trailer")
    private String trailer;

    @ManyToMany(mappedBy = "movies")
    private List<Character> characters;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
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
