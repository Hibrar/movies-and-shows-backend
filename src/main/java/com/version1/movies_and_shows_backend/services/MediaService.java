package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    MediaRepository mediaRepository;

    public Media getMediaById(String id) {
        return mediaRepository.findById(id).orElse(null);
    }

    public List<Media> getAllMedia() { return mediaRepository.findAll();}

    public List<Media> getByGenre(String genre) { return mediaRepository.findByGenres_NameIgnoreCase(genre);}

    public List<Media> getBySite(String site) { return mediaRepository.findBySites_NameIgnoreCase(site);}


    public Media getByTitle(String title) { return mediaRepository.findByTitleIgnoreCase(title).orElse(null);}


    // getByType
    // getByYear
    // getByAgeCert

    // probably going to need some custom queries, get by year > 1970

    //save...




}
