package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        String id = "tm1300";
        Media media = CreateSamples.media(id);
        when(mediaRepository.findById(id)).thenReturn(Optional.of(media));

        Media result = mediaService.getMediaById(id);
        assertEquals(result, media);
    }
}
