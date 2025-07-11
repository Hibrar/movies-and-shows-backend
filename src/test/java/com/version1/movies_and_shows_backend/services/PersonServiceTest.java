package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private Person person = CreateSamples.person();
    private List<Person> people = List.of(person);
    @Test
    public void getAllPeopleTest()
    {

        when(personRepository.findAll()).thenReturn(people);

        List<Person> result = personService.getAllPeople();
        assertEquals(result,people);
    }

    @Test
    public void getByNameTest()
    {
        when(personRepository.findByName("Peter Robbins")).thenReturn(Optional.of(person));
        Person result = personService.getByName("Peter Robbins");
        assertEquals(result, person);

    }

    @Test
    public void getByNameNotFound()
    {

        Person result = personService.getByName("Nathan");
        assertNull(result);
    }

}
