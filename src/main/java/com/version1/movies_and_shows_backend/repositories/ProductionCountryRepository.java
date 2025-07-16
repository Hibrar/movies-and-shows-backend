package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductionCountryRepository extends JpaRepository<ProductionCountry, Integer> {
    Optional<ProductionCountry> findByNameIgnoreCase(String name);
}
