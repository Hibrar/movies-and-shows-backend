package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

    @Autowired
    MediaRepository mediaRepository;

    public Media getMediaById(String id) {
        return mediaRepository.findById(id).orElse(null);
    }

    //getAll
        //getByGenre
        //getBySite

    //getByName

    //save...




}
