package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.ProductionCountry;
import com.version1.movies_and_shows_backend.models.Site;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@EntityScan(basePackages = "com.version1.movies_and_shows_backend.models")
public class MediaRepositoryTest {
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    final List<Media> media = new ArrayList<>(  List.of(new Media(
            "tm1300",
            "A Charlie Brown Christmas",
            "MOVIE",
            "When Charlie Brown complains about the overwhelming materialism that he sees amongst everyone during...",
            1965,
            "G",
            25,
            new ArrayList<>(List.of(
                    new Genre( "comedy"),
                    new Genre("drama"),
                    new Genre("family"),
                    new Genre("music"),
                    new Genre("animation"))),
            new ArrayList<>(List.of(new ProductionCountry("US"))), // US
            0,
            "tt0059026",
            8.3,
            40328,
            10.848,
            7.688,
            new ArrayList<>(List.of(new Site("Apple"),new Site("Netflix")))    )));

    @BeforeEach
    public void setup() {
        // Merge genres, sites, and production countries first
        for (Genre genre : media.getFirst().getGenres()) {
            testEntityManager.persist(genre);
        }
        for (Site site : media.getFirst().getSites()) {
            testEntityManager.persist(site);
        }
        for (ProductionCountry pc : media.getFirst().getProductionCountries()) {
            testEntityManager.persist(pc);
        }

        // Now persist Media
        testEntityManager.persistAndFlush(media.getFirst());
    }

    @Test
    public void saveMediaTest()
    {
        Media result = mediaRepository.save(media.getFirst());
        assertEquals(media.getFirst(),result);
    }

    @Test
    public void findByIdTest()
    {
        Optional <Media> result = mediaRepository.findById("tm1300");

        assertTrue(result.isPresent());
    }
    @Test
    public void findByIdTestNotFound()
    {
        Optional <Media> result = mediaRepository.findById("2");

        assertTrue(result.isEmpty());
    }


    @Test
    public void findAllTest()
    {
        List<Media> result = mediaRepository.findAll();
        assertEquals(media,result);
    }

    // Uncomment this test if you want to check the behavior when no media is found
    // This test is commented out because it requires an empty database setup
//    @Test
//    public void findAllNotFoundTest()
//    {
//        List<Media> result = mediaRepository.findAll();
//
//        assertTrue(result.isEmpty());
//    }

    @Test
    public void findByGenreTest()
    {
        List<Media> result = mediaRepository.findByGenres_NameIgnoreCase("comedy");
        assertEquals(media,result);

    }

    @Test
    public void findByGenreNotFoundTest()
    {
        List<Media> result = mediaRepository.findByGenres_NameIgnoreCase("nonexistent");
        assertTrue(result.isEmpty());
    }

    @Test
    public void findBySiteTest()
    {
        List<Media> result = mediaRepository.findBySites_NameIgnoreCase("Apple");
        assertEquals(media,result);
    }

    @Test
    public void findBySiteNotFoundTest()
    {
        List<Media> result = mediaRepository.findBySites_NameIgnoreCase("nonexistent");
        assertTrue(result.isEmpty());
    }


    @Test
    public void findByTitleTest() {
        Optional<Media> result = mediaRepository.findByTitleIgnoreCase("A Charlie Brown Christmas");
        assertTrue(result.isPresent());
        assertEquals(media.getFirst(), result.get());
    }
    @Test
    public void findByTitleNotFoundTest() {
        Optional<Media> result = mediaRepository.findByTitleIgnoreCase("Nonexistent Title");
        assertTrue(result.isEmpty());
    }

    @Test
    public void deleteByIdTest() {
        // Ensure the media exists before deletion
        Optional<Media> mediaBeforeDelete = mediaRepository.findById("tm1300");
        assertTrue(mediaBeforeDelete.isPresent());

        // Delete the media
        mediaRepository.deleteById("tm1300");

        // Assert that it no longer exists
        Optional<Media> mediaAfterDelete = mediaRepository.findById("tm1300");

        assertTrue(mediaAfterDelete.isEmpty());
    }

    @Test
    public void deleteByIdNotFoundTest() {
        // Attempt to delete a non-existing media
        mediaRepository.deleteById("nonexistent");

        // Assert that no exception is thrown and the repository remains empty
        List<Media> allMedia = mediaRepository.findAll();
        assertTrue(!allMedia.isEmpty());
    }

    @Test
    public void updateMediaTest() {
        // Ensure the media exists before update
        Optional<Media> mediaBeforeUpdate = mediaRepository.findById("tm1300");
        assertTrue(mediaBeforeUpdate.isPresent());

        Media mediaToUpdate = mediaBeforeUpdate.get();
        mediaToUpdate.setTitle("Updated Title");

        // Save the updated media
        Media updatedMedia = mediaRepository.save(mediaToUpdate);

        // Assert that the title has been updated
        assertEquals("Updated Title", updatedMedia.getTitle());
    }

    @Test
    public void updateMediaNotFoundTest() {
        // Attempt to update a non-existing media
        Media nonExistingMedia = new Media("nonexistent", "Nonexistent Title", "MOVIE", "Description", 2023, "PG-13", 120, new ArrayList<>(), new ArrayList<>(), 0, "tt1234567", 7.5, 1000, 5.0, 6.0, new ArrayList<>());

        // Save the non-existing media
        Media result = mediaRepository.save(nonExistingMedia);

        // Assert that the result is not null (it should be saved)
        assertNotNull(result);
    }


    @Test
    public void findByTitleIgnoreCaseTest() {
        // Ensure the media exists before the test
        Optional<Media> mediaBeforeTest = mediaRepository.findById("tm1300");
        assertTrue(mediaBeforeTest.isPresent());

        // Test case-insensitive search
        Optional<Media> result = mediaRepository.findByTitleIgnoreCase("a charlie brown christmas");
        assertTrue(result.isPresent());
        assertEquals(media.getFirst(), result.get());
    }




}
