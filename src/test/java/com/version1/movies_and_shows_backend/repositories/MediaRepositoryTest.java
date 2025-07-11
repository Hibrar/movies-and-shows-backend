package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MediaRepositoryTest {
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    final List<Media> media = CreateSamples.media();

    @BeforeEach
    public void setup(){
        testEntityManager.persistAndFlush(media);
    }

    @Test
    public void saveMediaTest()
    {
        Media result = mediaRepository.save(media.getFirst());
        assertEquals(media,result);
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
    @Test
    public void findAllNotFoundTest()
    {
        List<Media> result = mediaRepository.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    public void findByGenre()
    {

    }



    // fing by genre
    // find by genre not foind
    // find by site
    // find by site not found
    // find by title
    // find by title not found
    //save, then assert not null
    // update
    // delete

}
