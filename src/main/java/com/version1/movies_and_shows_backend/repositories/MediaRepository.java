package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, String> {
    List<Media> findByGenre(String genre);

    List<Media> findBySite(String site);

    Optional<Media> findByTitle(String title);

}
