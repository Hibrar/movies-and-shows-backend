package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(CastPK.class)
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
