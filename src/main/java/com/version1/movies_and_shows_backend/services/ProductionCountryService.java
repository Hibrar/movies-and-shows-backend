package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.ProductionCountry;
import com.version1.movies_and_shows_backend.repositories.ProductionCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCountryService {
    @Autowired
    ProductionCountryRepository productionCountryRepository;

    public List<ProductionCountry> getAllProductionCountries(){ return productionCountryRepository.findAll();}

    // save...
}
