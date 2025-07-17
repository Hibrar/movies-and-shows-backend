package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.ProductionCountry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeedExtractGenresTest {

    private final Function<String[], List<Genre>> mockExtractor = row -> {
        List<Genre> genres = new ArrayList<>();
        String rawString = row[4];
        String[] genreArray = rawString.replaceAll("[\\[\\]]", "").split(",");
        for (String genre : genreArray) {
            String cleanGenre = genre.trim().replaceAll("['\"]", "");
            if (!cleanGenre.isEmpty()) {
                genres.add(new Genre(cleanGenre));
            }
        }

        return genres;
    };



    @Test
    void extraGenres_Returns() {

        List<String[]> rows = List.of(new String[]{"id1", "title1", "MOVIE", "...", "['documentation']"}, new String[]{"id2", "title2", "SHOW", "...", "['drama', 'sport']"}, new String[]{"id3", "title3", "MOVIE", "...", "['romance', 'documentation']"});
        Set<Genre> result = new Seed().extractGenres(rows, mockExtractor);

        assertEquals(4, result.size());
        assertTrue(result.contains(new Genre("documentation")));
        assertTrue(result.contains(new Genre("drama")));
        assertTrue(result.contains(new Genre("sport")));
        assertTrue(result.contains(new Genre("romance")));
    }

    @Test
    void testExtractCountriesWithEmptyInput() {
        List<String[]> rows = Collections.emptyList();
        Set<Genre> result = new Seed().extractGenres(rows, mockExtractor);
        assertTrue(result.isEmpty(), "Should return empty set for empty input");
    }

    @Test
    void testExtractCountriesWithNoCountryData() {
        List<String[]> rows = List.of(new String[]{"id1", "title1", "MOVIE", "...", "[]"}, new String[]{"id2", "title2", "SHOW", "...", ""});


        Set<Genre> result = new Seed().extractGenres(rows, mockExtractor);
        assertTrue(result.isEmpty(), "Should return empty set when no countries are found");
    }

}
