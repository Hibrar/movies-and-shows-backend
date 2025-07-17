package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.*;
import com.version1.movies_and_shows_backend.repositories.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class SeedSeedCreditsTest {

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

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CastRepository castRepository;

    @Test
    void testSeedCredits_savesPersonsAndCastToDatabase() {
        // Arrange
        List<Site> sites = List.of(new Site("test"), new Site("test-2"));
        siteRepository.saveAll(sites);

        List<Media> mediaList =seed.seedMedia(sites);

        // Act
        seed.seedCredits(sites, mediaList);

        // Assert
        List<Person> persons = personRepository.findAll();
        List<Cast> casts = castRepository.findAll();
        List<Media> updatedMedia = mediaRepository.findAll();

        assertFalse(persons.isEmpty(), "Persons should be saved");
        assertFalse(casts.isEmpty(), "Cast should be saved");
        assertEquals(mediaList.size(), updatedMedia.size(), "Media count should remain consistent");

        for (Cast cast : castRepository.findAll()) {
            assertNotNull(cast.getPerson(), "Cast should be linked to a Person");
            assertNotNull(cast.getMedia(), "Cast should be linked to a Media");
        }

        Set<String> personNames = personRepository.findAll().stream()
                .map(Person::getName)
                .collect(Collectors.toSet());

        assertEquals(personNames.size(), personRepository.count(), "Persons should be unique by name");

    }

    @Test
    void testSylvesterStalloneCastEntryExists() {
        // Arrange
        List<Site> sites = List.of(new Site("test"), new Site("test-2"));
        siteRepository.saveAll(sites);

        List<Media> seededMedia = seed.seedMedia(sites);
        seed.seedCredits(sites, seededMedia);

        // Act
        Optional<Person> stallone = personRepository.findByName("Sylvester Stallone");
        assertTrue(stallone.isPresent(), "Sylvester Stallone should be saved");

        List<Cast> stalloneCast = castRepository.findByPerson(stallone.get());
        assertFalse(stalloneCast.isEmpty(), "Sylvester Stallone should have cast entries");

        boolean foundRockyRole = stalloneCast.stream().anyMatch(c ->
                c.getCharacter().equals("Robert \"Rocky\" Balboa")
        );
        assertTrue(foundRockyRole, "Sylvester Stallone should be cast as Robert \"Rocky\" Balboa");

        // Optional: Check linkage to Media
        boolean linkedToMedia = stalloneCast.stream().anyMatch(c ->
                c.getMedia() != null && seededMedia.contains(c.getMedia())
        );
        assertTrue(linkedToMedia, "Cast entry should be linked to a valid Media");
    }

}
