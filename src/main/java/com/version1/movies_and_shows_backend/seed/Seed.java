package com.version1.movies_and_shows_backend.seed;

import com.opencsv.CSVReader;
import com.version1.movies_and_shows_backend.models.*;
import com.version1.movies_and_shows_backend.repositories.*;
import com.version1.movies_and_shows_backend.utils.CSVToModel;
import jakarta.persistence.EntityManager;
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

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CastRepository castRepository;

//    @Autowired
//    private EntityManager entityManager;

    Path getCSVPath(String site) {
        return Paths.get("").toAbsolutePath().resolve("data/" + site);
    }

    public void seedData() {
        String siteName = "netflix";
        Path filePath = getCSVPath(siteName);
        Site site = seedSite(siteName);

        // MEDIA
        List<String[]> titlesCsvRows = readCSV(filePath + "/titles.csv");

        Set<Genre> genres = extractGenres(titlesCsvRows);
        Set<ProductionCountry> countries = extractCountries(titlesCsvRows);

        genreRepository.saveAll(new ArrayList<>(genres));
        productionCountryRepository.saveAll(new ArrayList<>(countries));

        Map<String, Genre> genreMap = buildGenreMap();
        Map<String, ProductionCountry> productionCountryMap = buildCountryMap();

        List<Media> mediaList = buildMediaList(titlesCsvRows, site, genreMap, productionCountryMap);

        mediaRepository.saveAll(mediaList);


        // CREDITS
        List<String[]> creditsCsvRows = readCSV(filePath + "/credits.csv");
        List<Person> personList = new ArrayList<>();
        List<Cast> castList = new ArrayList<>();

        Map<String, Person> personMap = new HashMap<>();
        Map<String, Media> mediaMap = mediaList.stream()
                .collect(Collectors.toMap(Media::getId, media -> media));


        for (String[] row : creditsCsvRows) {

            String personId = row[0];
            String mediaId = row[1];
            String name = row[2];
            String character = row[3];
            String role = row[4];

            // Get or create Person
            Person person = personMap.get(personId);
            if (person == null) {
                person = new Person(parseInteger(personId), name);
                personMap.put(personId, person);
                personList.add(person);
            }

            // Get Media
            Media media = mediaMap.get(mediaId);


            // Create Cast
            Cast cast = new Cast(media, person, character, role);
            castList.add(cast);

            // Add Cast to Media
            media.getCast().add(cast);
        }


        personRepository.saveAll(personList);
        System.out.println("Seeding castlist");
        castRepository.saveAll(castList);
        System.out.println("Finished cast");
        mediaRepository.saveAll(mediaList);
        System.out.println("Finished media again");

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

    private static Integer parseInteger(String value) {
        try {
            return (value == null || value.isEmpty()) ? 0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
