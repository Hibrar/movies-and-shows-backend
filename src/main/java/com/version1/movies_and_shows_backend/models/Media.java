package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="media")
public class Media {

    @Id
    private String id;
    @Column
    private String title;
    @Column
    private String type;
    @Column
    @Lob
    private String description;
    @Column
    private int releaseYear;
    @Column
    private String ageCert;
    @Column
    private int runtime;
    @Column
    @ManyToMany
    private List<Genre> genres;
    @Column
    @ManyToMany
    private List<ProductionCountry> productionCountries;
    @Column
    private double seasons;
    @Column
    private String imdbId;
    @Column
    private double imdbScore;
    @Column
    private double imdbVotes;
    @Column
    private double tmdbPopularity;
    @Column
    private double tmdbScore;
    @Column
    @ManyToMany
    private List<Site> sites;
    @Column
    @OneToMany(mappedBy = "media")
    private List<Cast> cast;

    public Media() {
    }


    public Media(String id, String title, String type, String description, int releaseYear, String ageCert, int runtime, List<Genre> genres, List<ProductionCountry> productionCountries, double seasons, String imdbId, double imdbScore, int imdbVotes, double tmdbPopularity, double tmdbScore, List<Site> sites) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.releaseYear = releaseYear;
        this.ageCert = ageCert;
        this.runtime = runtime;
        this.genres = genres;
        this.productionCountries = productionCountries;
        this.seasons = seasons;
        this.imdbId = imdbId;
        this.imdbScore = imdbScore;
        this.imdbVotes = imdbVotes;
        this.tmdbPopularity = tmdbPopularity;
        this.tmdbScore = tmdbScore;
        this.sites = sites;
        this.cast= new ArrayList<>();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAgeCert() {
        return ageCert;
    }

    public void setAgeCert(String ageCert) {
        this.ageCert = ageCert;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public double getSeasons() {
        return seasons;
    }

    public void setSeasons(double seasons) {
        this.seasons = seasons;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public double getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(double imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public double getTmdbPopularity() {
        return tmdbPopularity;
    }

    public void setTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public double getTmdbScore() {
        return tmdbScore;
    }

    public void setTmdbScore(double tmdbScore) {
        this.tmdbScore = tmdbScore;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", ageCert='" + ageCert + '\'' +
                ", runtime=" + runtime +
                ", genres=" + genres.stream().map(Genre::getName).toList() +
                ", productionCountries=" + productionCountries.stream().map(ProductionCountry::getName).toList() +
                ", seasons=" + seasons +
                ", imdbId='" + imdbId + '\'' +
                ", imdbScore=" + imdbScore +
                ", imdbVotes=" + imdbVotes +
                ", tmdbPopularity=" + tmdbPopularity +
                ", tmdbScore=" + tmdbScore +
                ", sites=" + sites.stream().map(Site::getName).toList() +
                ", cast=" + cast.stream().map(c -> c.getPerson().getName() + " as " + c.getCharacter() + " ( " + c.getRole() + " )").toList() +
                '}';
    }

}
