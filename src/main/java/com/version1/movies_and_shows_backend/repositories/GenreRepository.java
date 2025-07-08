package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
