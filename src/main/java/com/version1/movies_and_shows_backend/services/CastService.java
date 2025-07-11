package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.CastId;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
import com.version1.movies_and_shows_backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastService {
    // cast and person have been combined into 1 service as they are so closely related

    @Autowired
    CastRepository castRepository;


    public Cast getById(CastId id) { return castRepository.findById(id).orElse(null);}

    // idk if should use media or media id, media seems easier with jpa
    public List<Cast> getByMedia(Media media) { return  castRepository.findByMedia(media);}

    public List<Cast> getByPerson(Person person) { return castRepository.findByPerson(person);}

    // might be list
    public Cast getByCharacter(String character) { return castRepository.findByCharacter(character).orElse(null);}

    public List<Cast> getByRole (String role) { return castRepository.findByRole(role);}


    //save...

}
