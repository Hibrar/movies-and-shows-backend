package com.version1.movies_and_shows_backend.models;

import java.io.Serializable;
import java.util.Objects;

// Defining the composite primary key for the cast table
public class CastPK implements Serializable {
    private int personId;
    private String mediaId;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CastPK castPK)) return false;
        return personId == castPK.personId && Objects.equals(mediaId, castPK.mediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, mediaId);
    }

}
