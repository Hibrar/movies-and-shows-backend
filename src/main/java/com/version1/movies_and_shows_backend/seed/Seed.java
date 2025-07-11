package com.version1.movies_and_shows_backend.seed;

import com.opencsv.CSVReader;
import com.version1.movies_and_shows_backend.models.*;
import com.version1.movies_and_shows_backend.repositories.*;
import com.version1.movies_and_shows_backend.utils.CSVToModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Seed {
    private static final Logger logger = LoggerFactory.getLogger(Seed.class);

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ProductionCountryRepository productionCountryRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private MediaRepository mediaRepository;

    Path getCSVPath(String site) {
        return Paths.get("").toAbsolutePath().resolve("data/" + site);
    }

    public void seedData() {
        String siteName = "netflix";
        String filePath = getCSVPath(siteName) + "/titles.csv";

        Site site = seedSite(siteName);
        List<String[]> csvRows = readCSV(filePath);

        Set<Genre> genres = extractGenres(csvRows);
        Set<ProductionCountry> countries = extractCountries(csvRows);

        genreRepository.saveAll(new ArrayList<>(genres));
        productionCountryRepository.saveAll(new ArrayList<>(countries));

        Map<String, Genre> genreMap = buildGenreMap();
        Map<String, ProductionCountry> productionCountryMap = buildCountryMap();

        List<Media> mediaList = buildMediaList(csvRows, site, genreMap, productionCountryMap);
        mediaRepository.saveAll(mediaList);
    }

    Site seedSite(String siteName) {
        Site site = new Site(siteName);
        siteRepository.save(site);
        System.out.println("Attempting to seed data from " + siteName);
        return site;
    }

    Set<Genre> extractGenres(List<String[]> rows) {
        return rows.stream().flatMap(row -> CSVToModel.extractGenres(row).stream()).collect(Collectors.toSet());
    }

    Set<ProductionCountry> extractCountries(List<String[]> rows) {
        return rows.stream().flatMap(row -> CSVToModel.extractProductionCountries(row).stream()).collect(Collectors.toSet());
    }

    Map<String, Genre> buildGenreMap() {
        return genreRepository.findAll().stream().collect(Collectors.toMap(Genre::getName, g -> g));
    }

    Map<String, ProductionCountry> buildCountryMap() {
        return productionCountryRepository.findAll().stream().collect(Collectors.toMap(ProductionCountry::getName, c -> c));
    }

    List<Media> buildMediaList(List<String[]> rows, Site site, Map<String, Genre> genreMap, Map<String, ProductionCountry> countryMap) {
        List<Media> mediaList = new ArrayList<>();
        for (String[] row : rows) {
            Media media = CSVToModel.extractMedia(row);

            List<Genre> mediaGenres = CSVToModel.extractGenres(row).stream().map(g -> genreMap.get(g.getName())).filter(Objects::nonNull).collect(Collectors.toList());

            List<ProductionCountry> mediaCountries = CSVToModel.extractProductionCountries(row).stream().map(c -> countryMap.get(c.getName())).filter(Objects::nonNull).collect(Collectors.toList());

            media.setGenres(mediaGenres);
            media.setProductionCountries(mediaCountries);
            media.setSites(Collections.singletonList(site));

            mediaList.add(media);
        }
        return mediaList;
    }

    List<String[]> readCSV(String file) {
        List<String[]> rows = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            csvReader.readNext();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                rows.add(nextRecord);
            }
        } catch (IOException e) {
            logger.error("Failed to read CSV file: {}", file, e);
        }
        return rows;
    }
}
