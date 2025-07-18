package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.repositories.SiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class SeedCombineTitlesCSVFilesTest {
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
    void testCombineTitleCSVFiles_withRealFiles() {
        // Arrange
        List<Site> sites = sitesRepository.findAll();

        // Act
        List<String[]> result = seed.combineTitleCSVFiles(sites);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(12, result.size());
        result.forEach(row -> {
            assertEquals(16, row.length, "Each row should have 16 columns");
            String id = row[0];
            assertNotNull(id, "Id should not be null");
            assertFalse(id.trim().isEmpty(), "Id should not be empty");
            String title = row[1];
            assertNotNull(title, "Title should not be null");
            assertFalse(title.trim().isEmpty(), "Title should not be empty");
        });

    }
}
