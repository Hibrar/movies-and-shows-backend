package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    public Genre getByName(String name) {
        return genreRepository.findByNameIgnoreCase(name).orElse(null);
    }


    // save...
}
