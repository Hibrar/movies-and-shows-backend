package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.CastPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<Cast, CastPK> {
    // I believe need to add a custom query to get cast because of the composite PK
}
