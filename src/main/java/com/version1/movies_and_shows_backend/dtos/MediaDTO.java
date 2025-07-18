package com.version1.movies_and_shows_backend.dtos;

import java.util.List;

public class MediaDTO {
    public String title;
    public List<GenreDTO> genres;

    public MediaDTO() {
    }

    public MediaDTO(String title, List<GenreDTO> genreDTOs) {
        this.title = title;
        this.genres = genreDTOs;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
