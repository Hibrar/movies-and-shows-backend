package com.version1.movies_and_shows_backend.utils;

import com.version1.movies_and_shows_backend.models.*;

import java.util.ArrayList;
import java.util.List;

public class CSVToModel {

    /*
    I changed some stuff with models to get it to be right
    the models probably need some add to list methods
    media now has cast list
    person now has a cast list called appearances
    it links them together properly, makes it a bit more confusing for adding data to the db tho

    Order:
        genres -> needed for media
        productionCountries -> needed for media
        site -> when looping through each site it needs to be added to media when object created
        media
        person -> needed for cast
        cast -> needs to be added to media

        duplicateHandler -> after all 5 sites done, deals with duplicates
        ( all sites on duplicates need to be added to list, data might be different between the
        duplicates, so need to figure out how to handle that)



     */

    // static variables to make it easier to deal with all the data at end
    public static List<Genre> genres;
    public static List<Site> sites; // probably hard code here
    public static List<ProductionCountry> productionCountries;
    public static List<Media> media;
    public static List<Person> persons;
    public static List<Cast> cast;


    // needs to void, needs to check if already exists in genre, if not it adds
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


    public static void productionCountries(String row){
        // loops through row, checks if already in pc list, if not
        // adds to pc list
    }

    // maybe pass in which site it is as a variable so can add to media
    public static void extractMedia(){
        List<Media> media = new ArrayList<>();
        // loop through each column
        // switch case for row name
        // non-list rows put straight into object
        // list items compare names and then add the objects to the lists
        // if site is in parameters then add

        // adds to media list
    }

    public static void extractPerson(String row){
        // loops through row
        // adds to list if doesnt exist
    }

    public static void extractCast()
    {
        // for each column
        // make new cast
        // check through list of media for the matching ids, add media to cast
        // check through list of person for matching ids, add person to cast
        // add character and role
        // add cast to cast list

        // add cast to media cast list
        // add cast to person cast (appearances) list

    }

    public static void duplicateHandler(){
        // after all 5 sites done, deals with duplicates
        //  ( all sites on duplicates need to be added to list, data might be different between the
        //  duplicates, so need to figure out how to handle that)
    }

    // brings it all together
    public static void placeHolderName(){
        // loops through all 5 title files
        // calls the methods
        // loops through all 5 credit files
        // calls the methods

        // calls the duplicate handler

        // at this point the static variable lists should all have the correct data in
        // now would just need to add to the DB -> probably using its own method

    }
}
