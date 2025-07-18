package com.version1.movies_and_shows_backend.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.dtos.MediaDTO;
import com.version1.movies_and_shows_backend.exceptions.GenreNotFoundException;
import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.mappers.MediaMapper;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.services.GenreService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenresController.class)
public class GenresControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GenreService genreService;



    @Test
    public void testGetGenreByName() throws Exception {
        Genre genre = new Genre("drama");
        when(genreService.getGenreByName("drama")).thenReturn(new GenreDTO(genre.getId(), genre.getName()));

        mockMvc.perform(get("/genres/drama")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("drama"));
    }

    @Test
    public void getGenreByName_nonExistentGenre() throws Exception {
        String nonExistentGenre = "SciFantasy";
        when(genreService.getGenreByName("SciFantasy"))
                .thenThrow(new GenreNotFoundException("Genre not found: SciFantasy"));

        mockMvc.perform(get("/genres/" + nonExistentGenre))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.message").value("Genre not found: " + nonExistentGenre));

    }

    @Test
    public void testGetGenreMedia() throws Exception {
        List<Media> mediaList = CreateSamples.media();

        List<MediaDTO> dramaMediaDTOs = mediaList.stream().filter(media -> media.getGenres().stream().anyMatch(genre -> "drama".equalsIgnoreCase(genre.getName()))).map(MediaMapper::toDTO).toList();

        when(genreService.getGenreMedia("drama")).thenReturn(dramaMediaDTOs);

        MvcResult result = mockMvc.perform(get("/genres/drama/movies")).andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();

        List<MediaDTO> returnedMedia = mapper.readValue(json, new TypeReference<>() {
        });

        for (MediaDTO media : returnedMedia) {
            boolean hasDrama = media.getGenres().stream().anyMatch(genre -> "drama".equalsIgnoreCase(genre.getName()));

            assertTrue(hasDrama, "Expected media to include genre 'drama': " + media.getTitle());
        }
    }
}
