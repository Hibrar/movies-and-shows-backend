package com.version1.movies_and_shows_backend.controllers;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.dtos.MediaDTO;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {
    @Autowired
    private GenreService genreService;

    // GET /genres
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    // GET /genres/{name}
    @GetMapping("/{name}")
    public GenreDTO getGenreByName(@PathVariable String name) {
        return genreService.getGenreByName(name);
    }

    // GET /genres/{name}/movies
    @GetMapping("/{name}/movies")
    public List<MediaDTO> getGenreMovies(@PathVariable String name) {
        return genreService.getGenreMedia(name);
    }

    // GET /genres/{name}/analytics
//    @GetMapping("/{name}/analytics")
//    public GenreAnalytics getGenreAnalytics(@PathVariable String name) {
//        return genreService.getGenreAnalytics(name);
//    }
}
