package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPeople() { return  personRepository.findAll();}

    public  Person getByName(String name) { return personRepository.findByName(name).orElse(null);}

}
