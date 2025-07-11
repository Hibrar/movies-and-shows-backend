package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaService mediaService;

    public String id = "tm1300";
    public Media media = CreateSamples.media(id);
    @Test
    public void getMediaByIdTest() {

        when(mediaRepository.findById(id)).thenReturn(Optional.of(media));

        Media result = mediaService.getMediaById(id);
        assertEquals(result, media);

    }

    @Test
    public void getMediaByIdNotFoundTest(){
        Media result = mediaService.getMediaById("2");
        assertNull(result);
    }

    @Test
    public void getAllMediaTest(){
        List<Media> medias = List.of(media);
        when(mediaRepository.findAll()).thenReturn(medias);

        List<Media> result = mediaService.getAllMedia();
        assertEquals(result, medias);

    }

    @Test
    public void getByGenreTest(){
        List<Media> medias = List.of(media);
        when(mediaRepository.findByGenres_NameIgnoreCase("comedy")).thenReturn(medias);
        List<Media> result = mediaService.getByGenre("comedy");
        assertEquals(result, medias);

        when(mediaRepository.findByGenres_NameIgnoreCase("drama")).thenReturn(medias);
        result = mediaService.getByGenre("drama");
        assertEquals(result, medias);

    }

    @Test
    public void getByGenreNotFoundTest()
    {
        List<Media> result = mediaService.getByGenre("cheese");
        assertEquals(new ArrayList<Media>(), result);

        result = mediaService.getByGenre("romance");
        assertEquals(new ArrayList<Media>(), result);
    }

    @Test
    public void getBySiteTest()
    {
        List<Media> medias = List.of(media);
        when(mediaRepository.findBySites_NameIgnoreCase("Apple")).thenReturn(medias);
        List<Media> result = mediaService.getBySite("Apple");
        assertEquals(result, medias);


    }

    @Test
    public void getBySiteNotFoundTest()
    {
        List<Media> result = mediaService.getBySite("Netflix");
        assertEquals(new ArrayList<Media>(), result);

        result = mediaService.getBySite("Amazon");
        assertEquals(new ArrayList<Media>(), result);
    }


    @Test
    public  void getByTitleTest()
    {
        when(mediaRepository.findByTitle("A Charlie Brown Christmas")).thenReturn(Optional.of(media));

        Media result = mediaService.getByTitle("A Charlie Brown Christmas");
        assertEquals(result, media);
    }

    @Test
    public void getByTitleNotFoundTest()
    {
        Media result = mediaService.getByTitle("Boss Baby Back In Business");
        assertNull(result);
    }

}
