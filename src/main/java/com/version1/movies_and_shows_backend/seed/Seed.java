package com.version1.movies_and_shows_backend.seed;

import com.opencsv.CSVReader;
import com.version1.movies_and_shows_backend.models.*;
import com.version1.movies_and_shows_backend.repositories.*;
import com.version1.movies_and_shows_backend.utils.CSVToModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Seed {
    private static final Logger logger = LoggerFactory.getLogger(Seed.class);
    private static final int BATCH_SIZE = 100;
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

    // Seeding and Combining
    @Autowired
    private CastRepository castRepository;

    static Integer parseInteger(String value) {
        try {
            return (value == null || value.isEmpty()) ? 0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void seedData(List<String> siteNames) {
        List<Site> sites = seedSites(siteNames);

        List<Media> mediaList = seedMedia(sites);
        seedCredits(sites, mediaList);

    }

    List<String[]> combineTitleCSVFiles(List<Site> sites) {
        List<String[]> allCsvRows = new ArrayList<>();
        for (Site site : sites) {
            String siteName = site.getName();
            Path filePath = getCSVPath(siteName);
            List<String[]> csvRows = readCSV(filePath + "/titles.csv", siteName);

            allCsvRows.addAll(csvRows);
        }
        return allCsvRows;
    }

    List<String[]> combineCreditsCSVFiles(List<Site> sites) {
        List<String[]> allCsvRows = new ArrayList<>();
        Set<String> seenCredits = new HashSet<>();
        for (Site site : sites) {
            String siteName = site.getName();
            Path filePath = getCSVPath(siteName);
            List<String[]> csvRows = readCSV(filePath + "/credits.csv", siteName);

            for (String[] row : csvRows) {
                String mediaId = row[1];
                String name = row[2];
                String key = mediaId + "|" + name;
                if (!seenCredits.contains(key)) {
                    seenCredits.add(key);
                    allCsvRows.add(row);
                }
            }
        }
        return allCsvRows;
    }

    List<Media> seedMedia(List<Site> sites) {

        List<String[]> titlesCsvRows = combineTitleCSVFiles(sites);

        Set<Genre> genres = extractGenres(titlesCsvRows, CSVToModel::extractGenres);
        Set<ProductionCountry> countries = extractCountries(titlesCsvRows, CSVToModel::extractProductionCountries);

        saveInBatches(new ArrayList<>(genres), genreRepository, "Genres");
        saveInBatches(new ArrayList<>(countries), productionCountryRepository, "Production Countries");

        Map<String, Genre> genreMap = buildGenreMap();
        Map<String, ProductionCountry> countryMap = buildCountryMap();

        List<Media> mediaList = buildMediaList(titlesCsvRows, sites, genreMap, countryMap);
        saveInBatches(mediaList, mediaRepository, "Media");
        return mediaList;
    }

    void seedCredits(List<Site> sites, List<Media> mediaList) {
        List<String[]> creditsCsvRows = combineCreditsCSVFiles(sites);

        List<Person> personList = new ArrayList<>();
        List<Cast> castList = new ArrayList<>();
        Map<String, Person> personMap = new HashMap<>();
        Map<String, Media> mediaMap = buildMediaMap(mediaList);

        for (String[] row : creditsCsvRows) {
            processCreditRow(row, personMap, personList, castList, mediaMap);
        }

        saveInBatches(personList, personRepository, "Person");
        saveInBatches(castList, castRepository, "Cast");
        saveInBatches(mediaList, mediaRepository, "Media");
    }

    List<Site> seedSites(List<String> siteNames) {
        System.out.println("Attempting to seed data from:");
        List<Site> sites = new ArrayList<>();
        for (String siteName : siteNames) {
            Site site = new Site(siteName);
            siteRepository.save(site);
            System.out.println(siteName);
            sites.add(site);
        }
        return sites;
    }

    // Mapping and Extraction

    Map<String, Genre> buildGenreMap() {
        return genreRepository.findAll().stream().peek(g -> {
            if (g.getName() == null) throw new NullPointerException("Genre name is null");
        }).collect(Collectors.toMap(Genre::getName, g -> g));
    }

    Map<String, ProductionCountry> buildCountryMap() {
        return productionCountryRepository.findAll().stream().peek(c -> {
            if (c.getName() == null) throw new NullPointerException("Production country name is null");
        }).collect(Collectors.toMap(ProductionCountry::getName, c -> c));
    }

    Map<String, Media> buildMediaMap(List<Media> mediaList) {
        return mediaList.stream().peek(m -> {
            if (m.getId() == null) throw new NullPointerException("Genre name is null");
        }).collect(Collectors.toMap(Media::getId, media -> media));
    }

    List<Media> buildMediaList(List<String[]> rows, List<Site> sites, Map<String, Genre> genreMap, Map<String, ProductionCountry> countryMap) {
        Map<String, Media> mediaMap = new HashMap<>();

        for (String[] row : rows) {
            Media media = CSVToModel.extractMedia(row);
            String siteName = row[row.length - 1]; // Site name is the last column
            Site site = sites.stream().filter(s -> s.getName().equalsIgnoreCase(siteName)).findFirst().orElse(null);

            if (site == null) {
                continue; // Skip if site not found
            }

            List<Genre> mediaGenres = CSVToModel.extractGenres(row).stream().map(g -> genreMap.get(g.getName())).filter(Objects::nonNull).collect(Collectors.toList());

            List<ProductionCountry> mediaCountries = CSVToModel.extractProductionCountries(row).stream().map(c -> countryMap.get(c.getName())).filter(Objects::nonNull).collect(Collectors.toList());

            String mediaKey = media.getId();

            if (mediaMap.containsKey(mediaKey)) {
                Media existingMedia = mediaMap.get(mediaKey);
                Set<Site> updatedSites = new HashSet<>(existingMedia.getSites());
                updatedSites.add(site);
                existingMedia.setSites(new ArrayList<>(updatedSites));
            } else {
                media.setGenres(mediaGenres);
                media.setProductionCountries(mediaCountries);
                media.setSites(Collections.singletonList(site));
                mediaMap.put(mediaKey, media);
            }
        }

        return new ArrayList<>(mediaMap.values());
    }

    // Parsing and Utility

    Set<Genre> extractGenres(List<String[]> rows, Function<String[], List<Genre>> extractor) {
        return rows.stream().flatMap(row -> extractor.apply(row).stream()).collect(Collectors.toSet());
    }

    Set<ProductionCountry> extractCountries(List<String[]> rows, Function<String[], List<ProductionCountry>> extractor) {
        return rows.stream().flatMap(row -> extractor.apply(row).stream()).collect(Collectors.toSet());
    }

    Path getCSVPath(String site) {
        return Paths.get("").toAbsolutePath().resolve("data/" + site);
    }

    List<String[]> readCSV(String file, String siteName) {
        List<String[]> rows = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            csvReader.readNext();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                String[] extendedRecord = Arrays.copyOf(nextRecord, nextRecord.length + 1);
                extendedRecord[nextRecord.length] = siteName;
                rows.add(extendedRecord);
            }
        } catch (IOException e) {
            logger.error("Failed to read CSV file: {}", file, e);
        }
        return rows;
    }

    // Batching

    void processCreditRow(String[] row, Map<String, Person> personMap, List<Person> personList, List<Cast> castList, Map<String, Media> mediaMap) {
        String personId = row[0];
        String mediaId = row[1];
        String name = row[2];
        String character = row[3];
        String role = row[4];

        Person person = personMap.computeIfAbsent(personId, id -> {
            Person newPerson = new Person(parseInteger(id), name);
            personList.add(newPerson);
            return newPerson;
        });

        Media media = mediaMap.get(mediaId);
        if (media != null) {
            Cast cast = new Cast(media, person, character, role);
            castList.add(cast);
            media.getCast().add(cast);
        }
    }

    private <T> void saveInBatches(List<T> entities, JpaRepository<T, ?> repository, String info) {
        int total = entities.size();
        int batchCount = (int) Math.ceil((double) total / BATCH_SIZE);

        for (int i = 0; i < entities.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, entities.size());
            List<T> batch = entities.subList(i, end);
            repository.saveAll(batch);
            repository.flush();

            int currentBatch = (i / BATCH_SIZE) + 1;
            System.out.printf("Saved %s batch %d of %d (%d items)\n", info, currentBatch, batchCount, batch.size());
        }
    }

}
