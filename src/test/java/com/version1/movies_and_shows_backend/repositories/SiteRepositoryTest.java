package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Site;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class SiteRepositoryTest {
    @Autowired
    SiteRepository siteRepository;

    @Autowired
    TestEntityManager testEntityManager;

    private final List<Site> sites = List.of(
            new Site("Apple"),
            new Site("Netflix")
    );

    @BeforeEach
    public void setup() {
        for (Site site : sites) {
            testEntityManager.persist(site);
        }
        testEntityManager.flush();
    }

    @Test
    public void saveSiteTest() {
        Site site = siteRepository.save(sites.get(0));
        assertNotNull(site);
        assertEquals(sites.get(0), site);

    }
    @Test
    public void findByIdTest() {
        Site site = siteRepository.findById(sites.get(0).getId()).orElse(null);
        assertNotNull(site);
        assertEquals(sites.get(0), site);
    }
    @Test
    public void findByIdNotFoundTest() {
        Site site = siteRepository.findById(999).orElse(null);
        assertNull(site);
    }
    @Test
    public void findAllTest() {
        List<Site> siteList = siteRepository.findAll();
        assertNotNull(siteList);
        assertEquals(sites.size(), siteList.size());
        assertTrue(siteList.containsAll(sites));
    }
    @Test
    public void findByNameTest() {
        Site site = siteRepository.findByNameIgnoreCase(sites.get(0).getName()).orElse(null);
        assertNotNull(site);
        assertEquals(sites.get(0), site);
    }
    @Test
    public void findByNameNotFoundTest() {
        Site site = siteRepository.findByNameIgnoreCase("NonExistent").orElse(null);
        assertNull(site);
    }
    @Test
    public void findByNameIgnoreCaseTest() {
        Site site = siteRepository.findByNameIgnoreCase(sites.get(1).getName().toLowerCase()).orElse(null);
        assertNotNull(site);
        assertEquals(sites.get(1), site);
    }
    @Test
    public void deleteSiteTest() {
        Site site = siteRepository.findById(sites.get(0).getId()).orElse(null);
        assertNotNull(site);
        siteRepository.delete(site);
        Site deletedSite = siteRepository.findById(sites.get(0).getId()).orElse(null);
        assertNull(deletedSite);
    }
}
