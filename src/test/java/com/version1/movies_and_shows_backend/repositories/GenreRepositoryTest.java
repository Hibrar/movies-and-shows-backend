package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;


    // needed for testing repos with extra methods, not really needed for genre
    @Autowired
        private TestEntityManager testEntityManager;

    final Genre genre = CreateSamples.genres().getFirst();


    @BeforeEach
    public void setup(){
        testEntityManager.persistAndFlush(genre);
    }



    // find by id
    // find by id non-existing
    // find all, assert not null, assert containts genre

    //save, then assert not null
    // update
    // delete




}
