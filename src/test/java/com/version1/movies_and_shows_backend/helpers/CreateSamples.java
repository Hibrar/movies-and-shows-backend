package com.version1.movies_and_shows_backend.helpers;

import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.ProductionCountry;
import com.version1.movies_and_shows_backend.models.Site;

import java.util.List;

public class CreateSamples {
    public static Media media(String id) {
        List<Genre> genres = List.of(
                new Genre(1, "comedy"),
                new Genre(2, "drama"),
                new Genre(3, "family"),
                new Genre(4, "music"),
                new Genre(5, "animation")
        );

        return new Media(
                id,
                "A Charlie Brown Christmas",
                "MOVIE",
                "When Charlie Brown complains about the overwhelming materialism that he sees amongst everyone during...",
                1965,
                "G",
                25,
                genres,
                List.of(new ProductionCountry(1, "US")),
                0,
                "tt0059026",
                8.3,
                40328,
                10.848,
                7.688,
                List.of(new Site(1, "Apple"))
        );
    }
}
