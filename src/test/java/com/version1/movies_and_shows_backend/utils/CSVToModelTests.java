
package com.version1.movies_and_shows_backend.utils;

import com.version1.movies_and_shows_backend.models.Genre;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVToModelTests {
    @Test
    public void TestCSVToModelGenre() {
        String[] csvRow = {
                "tm82169",
                "Rocky",
                "MOVIE",
                "When world heavyweight boxing champion, Apollo Creed wants to give an unknown fighter a shot at the title as a publicity stunt, his handlers choose palooka Rocky Balboa, an uneducated collector for a Philadelphia loan shark. Rocky teams up with trainer  Mickey Goldmill to make the most of this once in a lifetime break.",
                "1976",
                "PG",
                "119",
                "['drama', 'sport']",
                "['US']",
                "",
                "tt0075148",
                "8.1",
                "588100.0",
                "106.361",
                "7.782"
        };


        List<Genre> result = CSVToModel.extractGenres(csvRow);
        List<Genre> expected = List.of(
                new Genre("drama"),
                new Genre("sport")
        );
        assertEquals(result, expected);
    }
}
