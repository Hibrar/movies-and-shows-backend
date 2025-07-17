package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeedBuildGenreMapTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private Seed seed;

    @Test
    void buildGenreMap_ReturnsCorrectMap() {
        Genre drama = new Genre(1, "drama");
        Genre comedy = new Genre(2, "comedy");
        Genre action = new Genre(3, "action");

        when(genreRepository.findAll()).thenReturn(List.of(drama, comedy, action));

        Map<String, Genre> genreMap = seed.buildGenreMap();

        assertEquals(3, genreMap.size());
        assertEquals(drama, genreMap.get("drama"));
        assertEquals(comedy, genreMap.get("comedy"));
        assertEquals(action, genreMap.get("action"));
    }

    @Test
    void buildGenreMap_WithEmptyList() {
        when(genreRepository.findAll()).thenReturn(Collections.emptyList());

        Map<String, Genre> genreMap = seed.buildGenreMap();

        assertTrue(genreMap.isEmpty(), "Genre map should be empty");
    }

    @Test
    void buildGenreMap_ThrowsOnDuplicateNames() {
        Genre g1 = new Genre(1, "comedy");
        Genre g2 = new Genre(2, "comedy"); // duplicate name

        when(genreRepository.findAll()).thenReturn(List.of(g1, g2));

        assertThrows(IllegalStateException.class, () -> seed.buildGenreMap());
    }
    @Test
    void buildGenreMap_ThrowsOnNullName() {
        Genre g1 = new Genre(1, null);
        when(genreRepository.findAll()).thenReturn(List.of(g1));

        assertThrows(NullPointerException.class, () -> seed.buildGenreMap());
    }

}
