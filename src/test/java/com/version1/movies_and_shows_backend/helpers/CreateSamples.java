package com.version1.movies_and_shows_backend.helpers;

import com.version1.movies_and_shows_backend.models.*;

import java.util.List;

public class CreateSamples {

    // Cached sample data to avoid creating duplicate entity instances
    private static final List<Genre> genreList = List.of(
            new Genre(1, "comedy"),
            new Genre(2, "drama"),
            new Genre(3, "family"),
            new Genre(4, "music"),
            new Genre(5, "animation"),
            new Genre(6, "scifi"),
            new Genre(7, "action"),
            new Genre(8, "fantasy")
    );

    private static final List<ProductionCountry> productionCountryList = List.of(
            new ProductionCountry(1, "US"),
            new ProductionCountry(2, "JP")
    );

    private static final List<Site> siteList = List.of(
            new Site(1, "Apple"),
            new Site(2, "Netflix")
    );

    public static List<Genre> genres() {
        return genreList;
    }

    public static List<ProductionCountry> productionCountries() {
        return productionCountryList;
    }

    public static List<Site> sites() {
        return siteList;
    }

    public static List<Media> media() {
        return List.of(
                new Media(
                        "tm1300",
                        "A Charlie Brown Christmas",
                        "MOVIE",
                        "When Charlie Brown complains about the overwhelming materialism that he sees amongst everyone during...",
                        1965,
                        "G",
                        25,
                        genres(),
                        List.of(productionCountryList.get(0)), // US
                        0,
                        "tt0059026",
                        8.3,
                        40328,
                        10.848,
                        7.688,
                        List.of(siteList.get(0)) // Apple
                ),
                new Media(
                        "ts21223",
                        "Pokémon",
                        "SHOW",
                        "Join Ash accompanied by his partner Pikachu, as he travels through many regions, meets new friends and faces new challenges on his quest to become a Pokémon Master.",
                        1997,
                        "TV-Y7",
                        22,
                        List.of(
                                genreList.get(2), // family
                                genreList.get(0), // comedy
                                genreList.get(5), // scifi
                                genreList.get(7), // fantasy
                                genreList.get(6), // action
                                genreList.get(4)  // animation
                        ),
                        List.of(productionCountryList.get(1)), // JP
                        25,
                        "tt1306685",
                        7.4,
                        224,
                        534.831,
                        7.7,
                        List.of(siteList.get(1)) // Netflix
                )
        );
    }

    public static Person person() {
        return new Person(1, "Peter Robbins");
    }

    public static Cast cast(Media media, Person person) {
        return new Cast(
                media,
                person,
                "Charlie Brown",
                "Actor"
        );
    }
}
