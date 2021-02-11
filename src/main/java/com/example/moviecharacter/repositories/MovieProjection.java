package com.example.moviecharacter.repositories;

public interface MovieProjection {
    public Long getId();
    public String getTitle();
    public String getGenre();
    public String getDirector();
    public int getReleaseYear();
    public String getTrailer();
    public String getPicture();
}
