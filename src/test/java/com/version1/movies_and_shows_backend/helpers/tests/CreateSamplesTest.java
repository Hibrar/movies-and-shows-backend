package com.version1.movies_and_shows_backend.helpers.tests;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSamplesTest {
    @Test
    public void createSampleMedia() {
        String mediaId = "tm1300";
        Media media = CreateSamples.media().getFirst();

        assertNotNull(media);
        assertEquals(mediaId, media.getId());
        assertEquals("A Charlie Brown Christmas", media.getTitle());
        assertEquals("MOVIE", media.getType());
        assertEquals(1965, media.getReleaseYear());
        assertEquals("G", media.getAgeCert());
        assertEquals(25, media.getRuntime());
        assertEquals("tt0059026", media.getImdbId());
        assertEquals(8.3, media.getImdbScore());
        assertEquals(40328, media.getImdbVotes());
        assertEquals(10.848, media.getTmdbPopularity());
        assertEquals(7.688, media.getTmdbScore());

        // Check genres
        List<Genre> genres = media.getGenres();
        assertEquals(5, genres.size());
        assertTrue(genres.stream().anyMatch(g -> g.getName().equals("comedy")));

        // Check countries
        List<ProductionCountry> countries = media.getProductionCountries();
        assertEquals(1, countries.size());
        assertEquals("US", countries.getFirst().getName());

        // Check sites
        List<Site> sites = media.getSites();
        assertEquals(1, sites.size());
        assertEquals("Apple", sites.getFirst().getName());
    }
    @Test
    public void createSamplePerson(){
        Person person = CreateSamples.person();

        assertNotNull(person);
        assertEquals(1,person.getId());
        assertEquals("Peter Robbins", person.getName());
    }

    @Test
    public void createSampleCast(){
        // cast needs media to be able to be created, im unsure if this is the safest way to do it
        Media media = CreateSamples.media().getFirst();
        Person person = CreateSamples.person();
        Cast cast = CreateSamples.cast(media, person);

        assertNotNull(cast);
        // id
        assertEquals(media, cast.getMedia());
        assertEquals(person,cast.getPerson());
        assertEquals("Charlie Brown", cast.getCharacter());
        assertEquals("Actor", cast.getRole());
        assertEquals(person.getId(),cast.getPerson().getId());
        assertEquals(media.getId(), cast.getMedia().getId());


    }
}
