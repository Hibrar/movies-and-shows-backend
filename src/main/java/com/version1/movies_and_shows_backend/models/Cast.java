package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.*;

@Entity
@Table(name="media_cast")
public class Cast {

    @EmbeddedId
    private CastId id;


    @ManyToOne
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    private Media media;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "character", length = 400)
    private String character;
    private String role;

    public Cast(Media media, Person person, String character, String role) {
        this.media = media;
        this.person = person;
        this.character = character;
        this.role = role;
        this.id= new CastId(person.getId(),media.getId());
    }

    public Cast() {
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CastId getId() {
        return id;
    }

    public void setId(CastId id) {
        this.id = id;
    }

}
