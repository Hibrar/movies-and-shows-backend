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
                new Genre(5, "animation")
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
    public static Media media(String id) {


        return new Media(
                id,
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
        );
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
