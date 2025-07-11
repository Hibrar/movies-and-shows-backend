package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "person")
    private List<Cast> appearances;

    public Person() {
    }

    public Person(int id, String name){
        this.id=id;
        this.name=name;
        this.appearances=new ArrayList<>();
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

    public List<Cast> getAppearances() {
        return appearances;
    }

    public void setAppearances(List<Cast> appearances) {
        this.appearances = appearances;
    }
}
