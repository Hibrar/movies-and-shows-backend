package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.dtos.MediaDTO;
import com.version1.movies_and_shows_backend.exceptions.GenreNotFoundException;
import com.version1.movies_and_shows_backend.mappers.MediaMapper;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public GenreDTO getGenreByName(String name) {
        Genre genre = genreRepository.findByNameIgnoreCase(name).orElseThrow(() -> new GenreNotFoundException("Genre not found: " + name));
        return new GenreDTO(genre.getId(), genre.getName());
    }

    public List<MediaDTO> getGenreMedia(String name) {
        List<Media> mediaList = mediaRepository.findByGenres_NameIgnoreCase(name);
        return mediaList.stream().map(MediaMapper::toDTO).toList();
    }
}
