package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.*;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import com.version1.movies_and_shows_backend.repositories.ProductionCountryRepository;
import com.version1.movies_and_shows_backend.repositories.SiteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class SeedSeedMediaTest {

    @Autowired
    private Seed seed;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ProductionCountryRepository productionCountryRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Test
    void testSeedMedia_savesDataToInMemoryDatabase() {
        // Arrange
        List<Site> sites = List.of(new Site("test"), new Site("test-2"));
        siteRepository.saveAll(sites);

        // Act
        List<Media> seededMedia = seed.seedMedia(sites);

        // Assert
        List<Genre> genres = genreRepository.findAll();
        List<ProductionCountry> countries = productionCountryRepository.findAll();
        List<Media> media = mediaRepository.findAll();

        assertFalse(genres.isEmpty(), "Genres should be saved");
        assertFalse(countries.isEmpty(), "Production countries should be saved");
        assertFalse(media.isEmpty(), "Media should be saved");

        assertEquals(seededMedia.size(), media.size(), "Returned media list should match saved media count");
        assertEquals(10, media.size(), "Seeded media should equal 10 unique entries");

    }
}
