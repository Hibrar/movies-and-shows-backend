package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;
        return name.equalsIgnoreCase(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
