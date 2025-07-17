package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeedBuildMediaMapTest {

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private Seed seed;

    @Test
    void buildMediaMap_ReturnsCorrectMap() {
        Media m1 = CreateSamples.media().getFirst();
        Media m2 = CreateSamples.media().getLast();
        List<Media> mediaList = CreateSamples.media();
        assertEquals(2, mediaList.size());
        Map<String, Media> mediaMap = seed.buildMediaMap(mediaList);

        assertEquals(2, mediaMap.size());
        assertEquals(m1, mediaMap.get(m1.getId()));
        assertEquals(m2, mediaMap.get(m2.getId()));
    }

    @Test
    void buildMediaMap_WithEmptyList() {
        List<Media> mediaList = new ArrayList<>();
        Map<String, Media> mediaMap = seed.buildMediaMap(mediaList);

        assertTrue(mediaMap.isEmpty(), "Country map should be empty");
    }

    @Test
    void buildMediaMap_ThrowsOnDuplicateIds() {
        Media m1 = CreateSamples.media().getFirst();
        Media m2 = CreateSamples.media().getFirst();

        assertThrows(IllegalStateException.class, () -> seed.buildMediaMap(List.of(m1, m2)));
    }

    @Test
    void buildMediaMap_ThrowsOnNullId() {
        Media m1 = CreateSamples.media().getFirst();
        m1.setId(null);


        assertThrows(NullPointerException.class, () -> seed.buildMediaMap(List.of(m1)));
    }

}
