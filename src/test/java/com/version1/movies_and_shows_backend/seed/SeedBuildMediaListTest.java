package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SeedBuildMediaListTest {
    @Autowired
    private Seed seed;

    @Test
    void buildMediaList_withValidRowsAndMappings() {
        // Arrange
        Site site1 = new Site("test");
        Site site2 = new Site("test-2");

        Genre genre1 = new Genre("documentation");
        Genre genre2 = new Genre("drama");

        ProductionCountry country1 = new ProductionCountry("US");
        ProductionCountry country2 = new ProductionCountry("UK");

        Map<String, Genre> genreMap = Map.of(
                "documentation", genre1,
                "drama", genre2
        );

        Map<String, ProductionCountry> countryMap = Map.of(
                "US", country1,
                "UK", country2
        );

        List<Site> sites = List.of(site1, site2);

        // Simulate two rows with same media ID but different sites
        String[] row1 = new String[] {
                "ts300399", "Five Came Back", "SHOW", "Description", "1945", "TV-MA", "51",
                "[documentation]", "[US]", "1.0", "", "", "", "0.601", "", "test"
        };

        String[] row2 = new String[] {
                "ts300399", "Five Came Back", "SHOW", "Description", "1945", "TV-MA", "51",
                "[documentation]", "[US]", "1.0", "", "", "", "0.601", "", "test-2"
        };

        List<String[]> rows = List.of(row1, row2);

        // Act
        List<Media> mediaList = seed.buildMediaList(rows, sites, genreMap, countryMap);

        // Assert
        assertEquals(1, mediaList.size(), "Expected one unique media entry");

        Media media = mediaList.getFirst();
        assertEquals("ts300399", media.getId(), "Media ID should match");
        assertEquals("Five Came Back", media.getTitle(), "Media title should match");

        assertEquals(1, media.getGenres().size(), "Should have one genre");
        assertEquals("documentation", media.getGenres().getFirst().getName());

        assertEquals(1, media.getProductionCountries().size(), "Should have one country");
        assertEquals("US", media.getProductionCountries().getFirst().getName());

        assertEquals(2, media.getSites().size(), "Should be associated with both sites");
        Set<String> siteNames = media.getSites().stream().map(Site::getName).collect(Collectors.toSet());
        assertTrue(siteNames.contains("test"));
        assertTrue(siteNames.contains("test-2"));
    }

    @Test
    void buildMediaList_skipsRowWithUnknownSite() {
        // Arrange
        Site site1 = new Site("test");
        Site site2 = new Site("test-2");

        Genre genre1 = new Genre("documentation");
        Genre genre2 = new Genre("drama");

        ProductionCountry country1 = new ProductionCountry("US");
        ProductionCountry country2 = new ProductionCountry("UK");

        Map<String, Genre> genreMap = Map.of(
                "documentation", genre1,
                "drama", genre2
        );

        Map<String, ProductionCountry> countryMap = Map.of(
                "US", country1,
                "UK", country2
        );

        List<Site> sites = List.of(site1, site2);

        String[] row = new String[] {
                "ts999999", "Unknown Media", "SHOW", "Description", "2000", "PG", "60",
                "[documentation]", "[US]", "1.0", "", "", "", "0.5", "", "nonexistent-site"
        };
        String[] row2 = new String[] {
                "ts300399", "Five Came Back", "SHOW", "Description", "1945", "TV-MA", "51",
                "[documentation]", "[US]", "1.0", "", "", "", "0.601", "", "test-2"
        };

        // Act
        List<Media> mediaList = seed.buildMediaList(List.of(row, row2), sites, genreMap, countryMap);

        // Assert
        assertEquals(1, mediaList.size(), "Media list should skip when site is unknown");
        assertEquals("ts300399", mediaList.getFirst().getId(), "Incorrect ID");
    }

    @Test
    void buildMediaList_filtersUnknownGenre() {
        // Arrange
        Site site1 = new Site("test");
        Site site2 = new Site("test-2");

        Genre genre1 = new Genre("documentation");
        Genre genre2 = new Genre("drama");

        ProductionCountry country1 = new ProductionCountry("US");
        ProductionCountry country2 = new ProductionCountry("UK");

        Map<String, Genre> genreMap = Map.of(
                "documentation", genre1,
                "drama", genre2
        );

        Map<String, ProductionCountry> countryMap = Map.of(
                "US", country1,
                "UK", country2
        );

        List<Site> sites = List.of(site1, site2);

        String[] row = new String[] {
                "ts123456", "Genreless Media", "SHOW", "Description", "2010", "PG", "45",
                "[unknownGenre]", "[US]", "1.0", "", "", "", "0.5", "", "test"
        };

        String[] row2 = new String[] {
                "ts300399", "Five Came Back", "SHOW", "Description", "1945", "TV-MA", "51",
                "[documentation]", "[US]", "1.0", "", "", "", "0.601", "", "test-2"
        };

        // Act
        List<Media> mediaList = seed.buildMediaList(List.of(row, row2), sites, genreMap, countryMap);

        // Assert
        assertEquals(2, mediaList.size(), "Media should still be created");
        assertTrue(mediaList.getFirst().getGenres().isEmpty(), "Genres should be empty due to unknown genre");
        assertFalse(mediaList.getLast().getGenres().isEmpty(), "Genres should not be empty");
    }
    @Test
    void buildMediaList_filtersUnknownCountry() {
        // Arrange
        Site site1 = new Site("test");
        Site site2 = new Site("test-2");

        Genre genre1 = new Genre("documentation");
        Genre genre2 = new Genre("drama");

        ProductionCountry country1 = new ProductionCountry("US");
        ProductionCountry country2 = new ProductionCountry("UK");

        Map<String, Genre> genreMap = Map.of(
                "documentation", genre1,
                "drama", genre2
        );

        Map<String, ProductionCountry> countryMap = Map.of(
                "US", country1,
                "UK", country2
        );

        List<Site> sites = List.of(site1, site2);

        String[] row = new String[] {
                "ts654321", "Countryless Media", "SHOW", "Description", "2015", "PG", "30",
                "[documentation]", "[UnknownLand]", "1.0", "", "", "", "0.5", "", "test"
        };

        String[] row2 = new String[] {
                "ts300399", "Five Came Back", "SHOW", "Description", "1945", "TV-MA", "51",
                "[documentation]", "[US]", "1.0", "", "", "", "0.601", "", "test-2"
        };

        // Act
        List<Media> mediaList = seed.buildMediaList(List.of(row, row2), sites, genreMap, countryMap);

        // Assert
        assertEquals(2, mediaList.size(), "Media should still be created");
        assertTrue(mediaList.getFirst().getProductionCountries().isEmpty(), "Countries should be empty due to unknown country");
        assertFalse(mediaList.getLast().getProductionCountries().isEmpty(), "Production Countries should not be empty");

    }

}
