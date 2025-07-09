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

    private String character;
    private String role;
}
