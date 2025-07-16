package com.version1.movies_and_shows_backend.repositories;


import com.version1.movies_and_shows_backend.models.ProductionCountry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class ProductionCountryRepositoryTest {
    @Autowired
    ProductionCountryRepository productionCountryRepository;

    @Autowired
    TestEntityManager testEntityManager;

    private final List<ProductionCountry> productionCountries = List.of(
            new ProductionCountry("US"),
            new ProductionCountry("JP")
    );

    @BeforeEach
    public void setup() {
        for (ProductionCountry productionCountry : productionCountries) {
            testEntityManager.persist(productionCountry);
        }
        testEntityManager.flush();
    }

    @Test
    public void saveProductionCountryTest() {
        ProductionCountry productionCountry = productionCountryRepository.save(productionCountries.get(0));
        assertNotNull(productionCountry);
        assertEquals(productionCountries.get(0), productionCountry);
    }
    @Test
    public void findByIdTest() {
        ProductionCountry productionCountry = productionCountryRepository.findById(productionCountries.get(0).getId()).orElse(null);
        assertNotNull(productionCountry);
        assertEquals(productionCountries.get(0), productionCountry);
    }
    @Test
    public void findByIdNotFoundTest() {
        ProductionCountry productionCountry = productionCountryRepository.findById(999).orElse(null);
        assertNull(productionCountry);
    }
    @Test
    public void findAllTest() {
        List<ProductionCountry> productionCountryList = productionCountryRepository.findAll();
        assertNotNull(productionCountryList);
        assertEquals(productionCountries.size(), productionCountryList.size());
    }
    @Test
    public void findByNameTest() {
        ProductionCountry productionCountry = productionCountryRepository.findByNameIgnoreCase(productionCountries.get(0).getName()).orElse(null);
        assertNotNull(productionCountry);
        assertEquals(productionCountries.get(0), productionCountry);
    }
    @Test
    public void findByNameNotFoundTest() {
        ProductionCountry productionCountry = productionCountryRepository.findByNameIgnoreCase("Unknown").orElse(null);
        assertNull(productionCountry);
    }
    @Test
    public void findByNameIgnoreCaseTest() {
        ProductionCountry productionCountry = productionCountryRepository.findByNameIgnoreCase(productionCountries.get(0).getName().toLowerCase()).orElse(null);
        assertNotNull(productionCountry);
        assertEquals(productionCountries.get(0), productionCountry);
    }
}
