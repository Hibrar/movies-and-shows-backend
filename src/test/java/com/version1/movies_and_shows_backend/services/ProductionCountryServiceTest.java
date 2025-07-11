package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.ProductionCountry;
import com.version1.movies_and_shows_backend.repositories.ProductionCountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductionCountryServiceTest {
    @Mock
    private ProductionCountryRepository productionCountryRepository;

    @InjectMocks
    private  ProductionCountryService productionCountryService;

    private List<ProductionCountry> productionCountries = CreateSamples.productionCountries();

    @Test
    public void getAllProductionCountryTest()
    {
        when(productionCountryRepository.findAll()).thenReturn(productionCountries);
        List<ProductionCountry> result = productionCountryService.getAllProductionCountries();

        assertEquals(productionCountries,result);

    }

    @Test
    public void getAllProductionCountryNotFoundTest()
    {

        List<ProductionCountry> result = productionCountryService.getAllProductionCountries();

        assertEquals(new ArrayList<>(),result);

    }

}
