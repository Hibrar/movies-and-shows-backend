package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.ProductionCountry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeedExtractCountriesTest {

    private final Function<String[], List<ProductionCountry>> mockExtractor = row -> {
        String rawString = row[4];
        String[] countriesArray = rawString.replaceAll("[\\[\\]']", "").split(",");
        List<ProductionCountry> countries = new ArrayList<>();
        for (String country : countriesArray) {
            String cleanString = country.trim().replaceAll("['\"]", "");
            if (!cleanString.isEmpty()) {
                countries.add(new ProductionCountry(cleanString));
            }
        }
        return countries;
    };

    @Test
    void extraCountries_Returns() {
        List<String[]> rows = List.of(new String[]{"id1", "title1", "MOVIE", "...", "['US', 'GB']"}, new String[]{"id2", "title2", "SHOW", "...", "['US']"}, new String[]{"id3", "title3", "MOVIE", "...", "['FR']"});
        Set<ProductionCountry> result = new Seed().extractCountries(rows, mockExtractor);

        assertEquals(3, result.size());
        assertTrue(result.contains(new ProductionCountry("US")));
        assertTrue(result.contains(new ProductionCountry("GB")));
        assertTrue(result.contains(new ProductionCountry("FR")));
    }

    @Test
    void testExtractCountriesWithEmptyInput() {
        List<String[]> rows = Collections.emptyList();
        Set<ProductionCountry> result = new Seed().extractCountries(rows, mockExtractor);
        assertTrue(result.isEmpty(), "Should return empty set for empty input");
    }

    @Test
    void testExtractCountriesWithNoCountryData() {
        List<String[]> rows = List.of(new String[]{"id1", "title1", "MOVIE", "...", "[]"}, new String[]{"id2", "title2", "SHOW", "...", ""});


        Set<ProductionCountry> result = new Seed().extractCountries(rows, mockExtractor);
        assertTrue(result.isEmpty(), "Should return empty set when no countries are found");
    }

}
