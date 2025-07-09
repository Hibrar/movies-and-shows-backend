package com.version1.movies_and_shows_backend.utils;

import com.version1.movies_and_shows_backend.models.Genre;

import java.util.ArrayList;
import java.util.List;

public class CSVToModel {
    public static List<Genre> extractGenres(String row) {
        List<Genre> genres = new ArrayList<>();
        int genreStart = row.indexOf("[");
        int genreEnd = row.indexOf("]", genreStart);
        if (genreStart != -1 && genreEnd != -1) {

            String genreField = row.substring(genreStart + 1, genreEnd);
            // Remove quotes and split by comma
            String[] genreArray = genreField.split(",");

            for (String genre:genreArray) {
                String cleanGenre = genre.trim().replaceAll("['\"]", "");
                if(!cleanGenre.isEmpty()) {
                    genres.add(new Genre(cleanGenre));
                }
            }
        }
        return genres;
    }
}
