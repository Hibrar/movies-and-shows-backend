package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.mappers.GenreMapper;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService  genreService;

    final List<Genre> genres = CreateSamples.genres();

    @Test
    public void getAllGenresTest()
    {
        when(genreRepository.findAll()).thenReturn(genres);

        List<GenreDTO> result = genreService.getAllGenres(false);

        assertEquals(genres.stream().map(GenreMapper::toDTO).toList(),result);
    }

    @Test
    public void getAllGenresNotFoundTest()
    {

        List<GenreDTO> result = genreService.getAllGenres(false);

        assertEquals(new ArrayList<>(),result);
    }

}
