package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SeedProcessCreditRowTest {

    @Test
    void processCreditRow_CreatesNewPersonAndCast() {
        String[] row = {"1681","tm51227","Jon Favreau","Harold 'Happy' Hogan","ACTOR"};

        Map<String, Person> personMap = new HashMap<>();
        List<Person> personList = new ArrayList<>();
        List<Cast> castList = new ArrayList<>();
        Map<String, Media> mediaMap = new HashMap<>();

        Media media = CreateSamples.singleMedia();
        mediaMap.put("tm51227", media);

        new Seed().processCreditRow(row, personMap, personList, castList, mediaMap);

        // Person assertions
        assertEquals(1, personMap.size());
        assertEquals(1, personList.size());
        Person person = personMap.get("1681");
        assertNotNull(person);
        assertEquals("Jon Favreau", person.getName());

        // Cast assertions
        assertEquals(1, castList.size());
        Cast cast = castList.getFirst();
        assertEquals("Harold 'Happy' Hogan", cast.getCharacter());
        assertEquals("ACTOR", cast.getRole());
        assertEquals(media, cast.getMedia());
        assertEquals(person, cast.getPerson());

        // Media cast list
        assertEquals(1, media.getCast().size());
        assertEquals(cast, media.getCast().getFirst());
    }

    @Test
    void processCreditRow_ReusesExistingPerson() {
        String[] row = {"1681","tm51227","Jon Favreau","Harold 'Happy' Hogan","ACTOR"};

        Person existingPerson = new Person(1681, "Jon Favreau");
        Map<String, Person> personMap = new HashMap<>();
        personMap.put("1681", existingPerson);

        List<Person> personList = new ArrayList<>();
        List<Cast> castList = new ArrayList<>();
        Map<String, Media> mediaMap = new HashMap<>();

        Media media = CreateSamples.singleMedia();
        mediaMap.put("tm51227", media);

        new Seed().processCreditRow(row, personMap, personList, castList, mediaMap);

        // Person assertions
        assertEquals(1, personMap.size());
        // Person should not be added
        assertEquals(0, personList.size());

        // Cast still added
        assertEquals(1, castList.size());
        Cast cast = castList.getFirst();
        assertEquals("Harold 'Happy' Hogan", cast.getCharacter());
        assertEquals("ACTOR", cast.getRole());
        assertEquals(existingPerson, cast.getPerson());

    }

    @Test
    void processCreditRow_SkipsIfMediaMissing() {
        String[] row = {"1681","tm51227","Jon Favreau","Harold 'Happy' Hogan","ACTOR"};

        Map<String, Person> personMap = new HashMap<>();
        List<Person> personList = new ArrayList<>();
        List<Cast> castList = new ArrayList<>();
        Map<String, Media> mediaMap = new HashMap<>();

        new Seed().processCreditRow(row, personMap, personList, castList, mediaMap);

        // Person assertions
        assertEquals(1, personMap.size());
        // Person should be added
        assertEquals(1, personList.size());

        // Cast should not be added
        assertEquals(0, castList.size());

    }
}
