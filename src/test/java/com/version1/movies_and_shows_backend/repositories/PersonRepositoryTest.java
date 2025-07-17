package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    TestEntityManager testEntityManager;

    private final List<Person> people = List.of(
            new Person(1,"Charlie Brown"),
            new Person(2,"Snoopy")
    );

    @BeforeEach
    public void setup() {
        for (Person person : people) {
            testEntityManager.persist(person);
        }
        testEntityManager.flush();
    }

    @Test
    public void savePersonTest() {
        Person person = personRepository.save(people.get(0));
        assertNotNull(person);
        assertEquals(people.get(0), person);
    }
    @Test
    public void findByIdTest() {
        Person person = personRepository.findById(people.get(0).getId()).orElse(null);
        assertNotNull(person);
        assertEquals(people.get(0), person);
    }
    @Test
    public void findByIdNotFoundTest() {
        Person person = personRepository.findById(999).orElse(null);
        assertNull(person);
    }
    @Test
    public void findAllTest() {
        List<Person> personList = personRepository.findAll();
        assertNotNull(personList);
        assertEquals(people.size(), personList.size());
    }
    @Test
    public void deleteByIdTest() {
        personRepository.deleteById(people.get(0).getId());
        Person person = personRepository.findById(people.get(0).getId()).orElse(null);
        assertNull(person);
    }
    @Test
    public void findByNameTest() {
        Person person = personRepository.findByNameIgnoreCase(people.get(0).getName()).orElse(null);
        assertNotNull(person);
        assertEquals(people.get(0), person);
    }
    @Test
    public void findByNameNotFoundTest() {
        Person person = personRepository.findByNameIgnoreCase("Unknown").orElse(null);
        assertNull(person);
    }
    @Test
    public void findByNameIgnoreCaseTest() {
        Person person = personRepository.findByNameIgnoreCase(people.get(1).getName().toLowerCase()).orElse(null);
        assertNotNull(person);
        assertEquals(people.get(1), person);
    }
}
