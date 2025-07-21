package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByNameIgnoreCase(String name);

    @Query("SELECT new com.version1.movies_and_shows_backend.dtos.GenreDTO(" +
            "g.id, g.name, COUNT(DISTINCT m.id), COUNT(DISTINCT c.id)) " +
            "FROM Media m " +
            "JOIN m.genres g " +
            "LEFT JOIN m.cast c " +
            "GROUP BY g.id, g.name " +
            "ORDER BY g.name")
    List<GenreDTO> getGenreStats();
}
