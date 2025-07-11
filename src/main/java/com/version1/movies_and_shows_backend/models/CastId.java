package com.version1.movies_and_shows_backend.models;

import java.io.Serializable;
import java.util.Objects;

// Defining the composite primary key for the cast table
public class CastId implements Serializable {
    private int personId;
    private String mediaId;

    public CastId() {} // required by JPA

    public CastId(int personId, String mediaId) {
        this.personId = personId;
        this.mediaId = mediaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CastId castID)) return false;
        return Objects.equals(personId, castID.personId) &&
                Objects.equals(mediaId, castID.mediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, mediaId);
    }
}
