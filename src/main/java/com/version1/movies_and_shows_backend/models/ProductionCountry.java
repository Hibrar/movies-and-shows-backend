package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="production_countries")
public class ProductionCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public ProductionCountry() {
    }
    public ProductionCountry(String name) {
        this.name = name;
    }
    public ProductionCountry(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("{ id: %d, name: %s }", getId(), getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ProductionCountry productionCountry)) return false;
        return name.equalsIgnoreCase(productionCountry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
