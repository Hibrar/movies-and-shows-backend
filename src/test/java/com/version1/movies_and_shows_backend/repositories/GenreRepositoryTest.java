package com.version1.movies_and_shows_backend.repositories;


import com.version1.movies_and_shows_backend.models.Genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;


    @Autowired
    private TestEntityManager testEntityManager;

    private final List<Genre> genres = List.of(
            new Genre("comedy"),
            new Genre("drama"),
            new Genre("family"),
            new Genre("music"),
            new Genre("animation"),
            new Genre("scifi"),
            new Genre("action"),
            new Genre("fantasy")
    );


    @BeforeEach
    public void setup() {
        for (Genre genre : genres) {
            testEntityManager.persist(genre);
        }
        testEntityManager.flush();
    }

    @Test
    public void saveGenreTest() {
        Genre genre = genreRepository.save(genres.getFirst());
        assertNotNull(genre);
        assertEquals(genres.getFirst(), genre);
    }

    @Test
    public void findByIdTest() {
        Genre genre = genreRepository.findById(genres.getFirst().getId()).orElse(null);
        assertNotNull(genre);
        assertEquals(genres.getFirst(), genre);
    }

    @Test
    public void findByIdNotFoundTest() {
        Genre genre = genreRepository.findById(999).orElse(null);
        assertNull(genre);
    }

    @Test
    public void findAllGenresTest() {
        List<Genre> allGenres = genreRepository.findAll();
        assertNotNull(allGenres);
        assertEquals(genres.size(), allGenres.size());
        assertEquals(genres.getFirst().getName(), allGenres.get(0).getName());
    }

    @Test
    public void deleteGenreTest() {
        Genre genre = genreRepository.findById(genres.getFirst().getId()).orElse(null);
        assertNotNull(genre);
        genreRepository.delete(genre);
        Genre deletedGenre = genreRepository.findById(genres.getFirst().getId()).orElse(null);
        assertNull(deletedGenre);
    }

    @Test
    public void findByNameTest() {
        Genre genre = genreRepository.findByNameIgnoreCase(genres.getFirst().getName()).orElse(null);
        assertNotNull(genre);
        assertEquals(genres.getFirst().getName(), genre.getName());
    }

    @Test
    public void findByNameNotFoundTest() {
        Genre genre = genreRepository.findByNameIgnoreCase("NonExistentGenre").orElse(null);
        assertNull(genre);
    }

    @Test
    public void findByNameIgnoreCaseTest() {
        Genre genre = genreRepository.findByNameIgnoreCase(genres.getFirst().getName().toUpperCase()).orElse(null);
        assertNotNull(genre);
        assertEquals(genres.getFirst().getName(), genre.getName());
    }


}
