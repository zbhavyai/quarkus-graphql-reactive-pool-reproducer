package org.acme.microprofile.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class FilmService {

    public Uni<List<Film>> getAllFilms() {
        return Film.findAll().list();
    }
}
