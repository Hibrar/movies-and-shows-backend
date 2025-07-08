package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.repositories.CastRepository;
import com.version1.movies_and_shows_backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastService {
    // cast and person have been combined into 1 service as they are so closely related

    @Autowired
    CastRepository castRepository;

    @Autowired
    PersonRepository personRepository;

    //getByPK
    //getAllByMediaId
    //getAllByPersonId
    //getByPersonName
    //getByCharacterName
    //getByRole

    //save...

}
