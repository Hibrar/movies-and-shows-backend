package com.version1.movies_and_shows_backend.seed;

import com.version1.movies_and_shows_backend.models.*;
import com.version1.movies_and_shows_backend.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;



@ExtendWith(MockitoExtension.class)
class SeedTest {

    @Mock private SiteRepository siteRepository;
    @Mock private GenreRepository genreRepository;
    @Mock private ProductionCountryRepository productionCountryRepository;
    @Mock private MediaRepository mediaRepository;

    @InjectMocks
    private Seed seed;

    @Spy
    private Seed spyService;

    @Test
    void testSeedData_executesSeedingWorkflow() {
        // Arrange
        String siteName = "netflix";
        Path filePath = Paths.get("/mock/path/netflix");
        Site site = new Site(siteName);
        List<String[]> csvRows = List.of(new String[]{"row1"}, new String[]{"row2"});
        Set<Genre> genres = Set.of(new Genre("Action"));
        Set<ProductionCountry> countries = Set.of(new ProductionCountry("USA"));
        Map<String, Genre> genreMap = Map.of("Action", new Genre("Action"));
        Map<String, ProductionCountry> countryMap = Map.of("USA", new ProductionCountry("USA"));
        List<Media> mediaList = List.of(new Media());

        Seed service = spy(seed);

        doReturn(filePath).when(service).getCSVPath(siteName);
        doReturn(csvRows).when(service).readCSV(filePath + "/titles.csv");
        doReturn(genres).when(service).extractGenres(csvRows);
        doReturn(countries).when(service).extractCountries(csvRows);
        doReturn(genreMap).when(service).buildGenreMap();
        doReturn(countryMap).when(service).buildCountryMap();
        doReturn(mediaList).when(service).buildMediaList(eq(csvRows), any(Site.class), eq(genreMap), eq(countryMap));

        // Act
        service.seedData();

        // Assert
        verify(siteRepository).save(any(Site.class));
        verify(genreRepository).saveAll(new ArrayList<>(genres));
        verify(productionCountryRepository).saveAll(new ArrayList<>(countries));
        verify(mediaRepository).saveAll(mediaList);
    }
}
