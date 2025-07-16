package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.CastId;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
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
public class CastRepositoryTest {
    @Autowired
    CastRepository castRepository;
    @Autowired
    TestEntityManager testEntityManager;

    private final Media media = new Media(
            "tm1300",
            "A Charlie Brown Christmas",
            "MOVIE",
            "When Charlie Brown complains about the overwhelming materialism that he sees amongst everyone during...",
            1965,
            "G",
            25,
            new ArrayList<>(),
            new ArrayList<>(),
            0,
            "tt0059026",
            8.3,
            40328,
            10.848,
            7.688,
            new ArrayList<>()
    );
    private final Person person = new Person(1, "Peter Robbins");
    private final Cast cast = new Cast(media, person, "Charlie Brown", "Actor");

    @BeforeEach
    public void setup() {
        testEntityManager.persist(media);
        testEntityManager.persist(person);
        testEntityManager.persist(cast);
        testEntityManager.flush();
    }

    @Test
    public void saveCastTest() {
        Cast savedCast = castRepository.save(cast);
        assertNotNull(savedCast);
        assertEquals(cast, savedCast);
    }

    @Test
    public void findByIdTest() {
        Cast foundCast = castRepository.findById(cast.getId()).orElse(null);
        assertNotNull(foundCast);
        assertEquals(cast, foundCast);
    }
    @Test
    public void findByIdNotFoundTest() {
        Cast foundCast = castRepository.findById(new CastId()).orElse(null);
        assertNull(foundCast);
    }
    @Test
    public void findAllTest() {
        List<Cast> castList = castRepository.findAll();
        assertNotNull(castList);
        assertEquals(List.of(cast), castList);
    }
    @Test
    public void deleteByIdTest() {
        castRepository.deleteById(cast.getId());
        Cast foundCast = castRepository.findById(cast.getId()).orElse(null);
        assertNull(foundCast);
    }
    @Test
    public void findByMediaTest() {
        List<Cast> foundCasts = castRepository.findByMedia(media);
        assertNotNull(foundCasts);
        assertEquals(1, foundCasts.size());
        assertEquals(cast, foundCasts.get(0));
    }
    @Test
    public void findByMediaNotFoundTest() {
        Media unknownMedia = new Media();
        unknownMedia.setId("nonexistent_id");
        List<Cast> foundCasts = castRepository.findByMedia(unknownMedia);
        assertNotNull(foundCasts);
        assertTrue(foundCasts.isEmpty());
    }
    @Test
    public void findByPersonTest() {
        List<Cast> foundCasts = castRepository.findByPerson(person);
        assertNotNull(foundCasts);
        assertEquals(1, foundCasts.size());
        assertEquals(cast, foundCasts.get(0));
    }
    @Test
    public void findByPersonNotFoundTest() {
        Person unknownPerson = new Person();
        List<Cast> foundCasts = castRepository.findByPerson(unknownPerson);
        assertNotNull(foundCasts);
        assertTrue(foundCasts.isEmpty());
    }
    @Test
    public void findByCharacterTest() {
        Cast foundCast = castRepository.findByCharacterIgnoreCase("Charlie Brown").orElse(null);
        assertNotNull(foundCast);
        assertEquals(cast, foundCast);
    }
    @Test
    public void findByCharacterNotFoundTest() {
        Cast foundCast = castRepository.findByCharacterIgnoreCase("Unknown Character").orElse(null);
        assertNull(foundCast);
    }
    @Test
    public void findByCharacterIgnoreCaseTest() {
        Cast foundCast = castRepository.findByCharacterIgnoreCase("charlie brown").orElse(null);
        assertNotNull(foundCast);
        assertEquals(cast, foundCast);
    }

    @Test
    public void findByRoleTest() {
        List<Cast> foundCasts = castRepository.findByRoleIgnoreCase("Actor");
        assertNotNull(foundCasts);
        assertEquals(1, foundCasts.size());
        assertEquals(cast, foundCasts.get(0));
    }
    @Test
    public void findByRoleNotFoundTest() {
        List<Cast> foundCasts = castRepository.findByRoleIgnoreCase("Unknown Role");
        assertNotNull(foundCasts);
        assertTrue(foundCasts.isEmpty());
    }
    @Test
    public void findByRoleIgnoreCaseTest() {
        List<Cast> foundCasts = castRepository.findByRoleIgnoreCase("actor");
        assertNotNull(foundCasts);
        assertEquals(1, foundCasts.size());
        assertEquals(cast, foundCasts.get(0));
    }




}
