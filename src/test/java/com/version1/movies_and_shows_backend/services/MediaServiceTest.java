package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.ProductionCountry;
import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaService mediaService;

    @Test
    public void getMediaByIdTest() {
        List<Genre> genres = List.of(
                new Genre(1, "comedy"),
                new Genre(2, "drama"),
                new Genre(3, "family"),
                new Genre(4, "music"),
                new Genre(5, "animation")
        );

        Media media = new Media(
                "tm1300",
                "A Charlie Brown Christmas",
                "MOVIE",
                "When Charlie Brown complains about the overwhelming materialism that he sees amongst everyone during...",
                1965,
                "G",
                25,
                genres,
                List.of(new ProductionCountry(1, "US")),
                0,
                "tt0059026",
                8.3,
                40328,
                10.848,
                7.688,
                List.of(new Site(1, "Apple"))
        );

        String id = "tm1300";

        when(mediaRepository.findById(id)).thenReturn(Optional.of(media));

        Media result = mediaService.getMediaById(id);
        assertEquals(result, media);
    }
}
