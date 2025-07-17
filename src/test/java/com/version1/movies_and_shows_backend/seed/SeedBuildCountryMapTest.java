package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.ProductionCountry;
import com.version1.movies_and_shows_backend.repositories.ProductionCountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeedBuildCountryMapTest {

    @Mock
    private ProductionCountryRepository productionCountryRepository;

    @InjectMocks
    private Seed seed;

    @Test
    void buildCountryMap_ReturnsCorrectMap() {
        ProductionCountry us = new ProductionCountry(1, "US");
        ProductionCountry en = new ProductionCountry(2, "EN");
        ProductionCountry fr = new ProductionCountry(3, "FR");

        when(productionCountryRepository.findAll()).thenReturn(List.of(us, en, fr));

        Map<String, ProductionCountry> countryMap = seed.buildCountryMap();

        assertEquals(3, countryMap.size());
        assertEquals(us, countryMap.get("US"));
        assertEquals(en, countryMap.get("EN"));
        assertEquals(fr, countryMap.get("FR"));
    }

    @Test
    void buildProductionCountryMap_WithEmptyList() {
        when(productionCountryRepository.findAll()).thenReturn(Collections.emptyList());

        Map<String, ProductionCountry> countryMap = seed.buildCountryMap();

        assertTrue(countryMap.isEmpty(), "Country map should be empty");
    }

    @Test
    void buildProductionCountryMap_ThrowsOnDuplicateNames() {
        ProductionCountry c1 = new ProductionCountry(1, "US");
        ProductionCountry c2 = new ProductionCountry(2, "US"); // duplicate name

        when(productionCountryRepository.findAll()).thenReturn(List.of(c1, c2));

        assertThrows(IllegalStateException.class, () -> seed.buildCountryMap());
    }

    @Test
    void buildProductionCountryMap_ThrowsOnNullName() {
        ProductionCountry c1 = new ProductionCountry(1, null);
        when(productionCountryRepository.findAll()).thenReturn(List.of(c1));

        assertThrows(NullPointerException.class, () -> seed.buildCountryMap());
    }

}
