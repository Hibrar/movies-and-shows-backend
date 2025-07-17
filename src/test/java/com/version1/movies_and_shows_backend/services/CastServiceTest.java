package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.CastId;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CastServiceTest {
    @Mock
    private CastRepository castRepository;

    @InjectMocks
    private  CastService castService;

    final Person person = CreateSamples.person();
    final Media media = CreateSamples.media().getFirst();
    final Cast cast = CreateSamples.cast(media,person);
    final CastId castId = new CastId(person.getId(),media.getId());
    final List<Cast> castList = List.of(cast);

    @Test
    public void getCastByIdTest()
    {
        when(castRepository.findById(castId)).thenReturn(Optional.of(cast));
        Cast result = castService.getById(castId);

        assertEquals(result,cast);
    }

    @Test
    public void getCastByIdNotFound(){
        Cast result = castService.getById(new CastId(2,"3"));
         assertNull(result);
    }

    @Test
    public void getByMediaTest()
    {
        when(castRepository.findByMedia(media)).thenReturn(castList);

        List<Cast> result = castService.getByMedia(media);

        assertEquals(result,castList);

    }

    @Test
    public void getByMediaNotFoundTest()
    {
        List<Cast> result = castService.getByMedia(new Media());
        assertEquals(new ArrayList<>(),result);
    }

    @Test
    public void getByPersonTest()
    {
        when(castRepository.findByPerson(person)).thenReturn(castList);

        List<Cast> result = castService.getByPerson(person);
        assertEquals(castList,result);

    }

    @Test
    public void getByPersonNotFoundTest()
    {
        List<Cast> result = castService.getByPerson(new Person());
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void getByCharacterTest()
    {
        when(castRepository.findByCharacterIgnoreCase("Charlie Brown")).thenReturn(Optional.of(cast));
        Cast result = castService.getByCharacter("Charlie Brown");

        assertEquals(cast, result);
    }

    @Test
    public void getByCharacterNotFoundTest()
    {
        Cast result = castService.getByCharacter("Chicken Little");

        assertNull(result);
    }

    @Test
    public void getByRoleTest()
    {
        when(castRepository.findByRoleIgnoreCase("Actor")).thenReturn(castList);
        List<Cast> result = castService.getByRole("Actor");

        assertEquals(castList, result);
    }

    @Test
    public void getByRoleNotFoundTest()
    {
        List<Cast> result = castService.getByRole("Director");

        assertEquals(new ArrayList<>(),result);
    }

}
