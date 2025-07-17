package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.repositories.SiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SeedCombineCreditsCSVFilesTest {
    @Autowired
    private SiteRepository sitesRepository;

    @Autowired
    private Seed seed; // The class containing combineTitleCSVFiles

    @BeforeEach
    void setUp() {
        sitesRepository.deleteAll(); // Clean slate

        sitesRepository.save(new Site("test"));
        sitesRepository.save(new Site("test-2"));
    }

    @Test
    void testCombineCreditsCSVFiles_withRealFiles() {

        // Arrange
        List<Site> sites = sitesRepository.findAll();

        // Act
        List<String[]> result = seed.combineCreditsCSVFiles(sites);

        // Assert
        assertFalse(result.isEmpty(), "Credits CSV should not be empty");

        // Example: Expecting 45 unique credits
        assertEquals(45, result.size(), "Expected 45 credit entries");

        // Validate structure and content
        Set<String> seenKeys = new HashSet<>();
        result.forEach(row -> {
            assertEquals(6, row.length, "Each row should have at least 6 columns");

            String mediaId = row[1];
            String name = row[2];

            assertNotNull(mediaId, "ID should not be null");
            assertFalse(mediaId.trim().isEmpty(), "ID should not be empty");

            assertNotNull(name, "Name should not be null");
            assertFalse(name.trim().isEmpty(), "Name should not be empty");

            String key = mediaId + "|" + name;
            assertFalse(seenKeys.contains(key), "Duplicate credit found: " + key);
            seenKeys.add(key);
        });
    }
}
