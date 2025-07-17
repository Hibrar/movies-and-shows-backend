package com.version1.movies_and_shows_backend.seed;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeedReadCSVTest {

    @Test
    void readCSV_ReturnsCorrectArray() throws URISyntaxException {
        Seed seed = new Seed();
        Path path = seed.getCSVPath("test");
        String siteName = "FilmArchive";

        // Read the CSV
        List<String[]> result = new Seed().readCSV(path + "/titles.csv" , siteName);

        // Assert expected number of rows (10 data rows, header skipped)
        assertEquals(10, result.size(), "Should read 10 data rows");

        // Assert first row content
        String[] firstRow = result.getFirst();
        assertEquals("ts300399", firstRow[0]);
        assertEquals("Five Came Back: The Reference Films", firstRow[1]);
        assertEquals("SHOW", firstRow[2]);
        assertEquals("FilmArchive", firstRow[firstRow.length - 1]);

        // Assert last row content
        String[] lastRow = result.getLast();
        assertEquals("tm16479", lastRow[0]);
        assertEquals("White Christmas", lastRow[1]);
        assertEquals("MOVIE", lastRow[2]);
        assertEquals("FilmArchive", lastRow[lastRow.length - 1]);

        // Assert all rows have siteName appended
        for (String[] row : result) {
            assertEquals(siteName, row[row.length - 1], "Site name should be appended to each row");
        }
    }
}
