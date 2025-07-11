package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.CastId;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CastRepository extends JpaRepository<Cast, CastId> {
    // I believe need to add a custom query to get cast because of the composite PK

    List<Cast> findByMedia (Media media);

    List<Cast> findByPerson (Person person);

    Optional<Cast> findByCharacter(String Character);

    List<Cast> findByRole(String role);

}
