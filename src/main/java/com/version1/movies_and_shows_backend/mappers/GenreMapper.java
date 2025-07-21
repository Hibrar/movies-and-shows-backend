package com.version1.movies_and_shows_backend.mappers;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.dtos.MediaDTO;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.Media;

import java.util.List;
import java.util.stream.Collectors;

public class GenreMapper {

    public static GenreDTO toDTO(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }
}
