package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
import com.version1.movies_and_shows_backend.repositories.SiteRepository;
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
public class SiteServiceTest {
    @Mock
    private SiteRepository siteRepository;

    @InjectMocks
    private SiteService siteService;

    final List<Site> sites = CreateSamples.sites();

    @Test
    public void getAllSitesTest()
    {
        when(siteRepository.findAll()).thenReturn(sites);
        List<Site> result = siteService.getAllSites();

        assertEquals(sites, result);

    }

    @Test
    public void getAllSitesNotFoundTest()
    {

        List<Site> result = siteService.getAllSites();

        assertEquals(new ArrayList<>(), result);

    }

}
