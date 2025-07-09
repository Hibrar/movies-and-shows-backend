package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.*;

@Entity
@Table(name="media_cast")
@IdClass(CastPK.class) // Composite PK
public class Cast {

    @Id
    private int personId;
    @Id
    private String mediaId;

    @Column
    private String character;

    @Column
    private String role;
}
