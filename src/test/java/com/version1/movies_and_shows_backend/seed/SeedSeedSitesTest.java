package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.repositories.SiteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class SeedSeedSitesTest {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private Seed seed;

    @Test
    void seedSites_PersistsSites() {
        List<String> siteNames = List.of("Site1", "Site2");

        List<Site> result = seed.seedSites(siteNames);

        List<Site> allSites = siteRepository.findAll();
        assertEquals(2, allSites.size());
        assertEquals(result, allSites);
        assertTrue(allSites.stream().anyMatch(site -> site.getName().equals("Site1")));
        assertTrue(allSites.stream().anyMatch(site -> site.getName().equals("Site2")));
    }
}


