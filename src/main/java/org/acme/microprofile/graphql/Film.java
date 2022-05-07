package org.acme.microprofile.graphql;

import java.time.LocalDate;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Cacheable
@Table(name = "film")
public class Film extends PanacheEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "episodeid")
    private Integer episodeID;

    @Column(name = "director")
    private String director;

    @Column(name = "releasedate")
    private LocalDate releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeID() {
        return episodeID;
    }

    public void setEpisodeID(Integer episodeID) {
        this.episodeID = episodeID;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}