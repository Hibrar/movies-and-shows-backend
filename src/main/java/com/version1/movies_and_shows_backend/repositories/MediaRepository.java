package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, String> {
}
