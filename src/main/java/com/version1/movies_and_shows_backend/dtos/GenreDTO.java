package com.version1.movies_and_shows_backend.dtos;

import java.util.Objects;

public class GenreDTO {
    private int id;
    private String name;
    private Long mediaCount;
    private Long castCount;

    public GenreDTO() {
    }

    public GenreDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreDTO(int id, String name, Long mediaCount, Long castCount) {
        this.id = id;
        this.name = name;
        this.mediaCount = mediaCount;
        this.castCount = castCount;
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

    public Long getCastCount() {
        return castCount;
    }

    public void setCastCount(Long castCount) {
        this.castCount = castCount;
    }

    public Long getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(Long mediaCount) {
        this.mediaCount = mediaCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreDTO that)) return false;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(mediaCount, that.mediaCount) &&
                Objects.equals(castCount, that.castCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mediaCount, castCount);
    }
}
