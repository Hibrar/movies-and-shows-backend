package com.version1.movies_and_shows_backend.controllers;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaRepository mediaRepository;
    // GET /genres
    @Transactional
    @GetMapping("/{title}")
    public void getMedia(@PathVariable String title) {

        Optional<Media> media = mediaRepository.findFirstByTitleIgnoreCase(title);
        media.ifPresentOrElse(
                value -> System.out.println(value.getGenres().getFirst().getName()),
                () -> System.out.println("not found")
        );
    }

}
