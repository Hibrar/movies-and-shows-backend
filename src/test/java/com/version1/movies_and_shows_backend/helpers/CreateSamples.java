package com.version1.movies_and_shows_backend.helpers;

import com.version1.movies_and_shows_backend.models.*;

import java.util.List;

public class CreateSamples {

    public static List<Genre> genres()
    {
        return List.of(
                new Genre(1, "comedy"),
                new Genre(2, "drama"),
                new Genre(3, "family"),
                new Genre(4, "music"),
                new Genre(5, "animation"),
                new Genre(6, "scifi"),
                new Genre(7, "action"),
                new Genre(8, "fantasy")
        );
    }

    public static  List<ProductionCountry> productionCountries()
    {
        return  List.of(new ProductionCountry(1, "US"));
    }

    public static List<Site> sites()
    {
        return List.of(new Site(1, "Apple"));
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
                productionCountries(),
                0,
                "tt0059026",
                8.3,
                40328,
                10.848,
                7.688,
                sites()
        ),
        new Media(
                "ts21223",
                "Pokémon",
                "SHOW",
                "Join Ash accompanied by his partner Pikachu, as he travels through many regions, meets new friends and faces new challenges on his quest to become a Pokémon Master.",
                1997,
                "TV-Y7",
                22,
                List.of(genres().get(2),genres().get(0), genres().get(5),genres().get(7),genres().get(6),genres().get(4)),
                List.of(new ProductionCountry("JP")),
                25,
                "tt1306685",
                7.4,
                224,
                534.831,
                7.7,
                List.of(new Site("Netflix"))

        ));
    }

    public static Person person(){
        return new Person(1,"Peter Robbins");
    }
    public static Cast cast(Media media, Person person){

        return new Cast(
                media,
                person,
                "Charlie Brown",
                "Actor",
                person.getId(),
                media.getId()
        );
    }
}
