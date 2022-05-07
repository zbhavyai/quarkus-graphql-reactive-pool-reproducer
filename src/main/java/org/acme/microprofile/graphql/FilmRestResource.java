package org.acme.microprofile.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.smallrye.mutiny.Uni;

@Path("rest")
@ApplicationScoped
public class FilmRestResource {

    @Inject
    FilmService service;

    @GET
    @Path("allFilms")
    public Uni<List<Film>> getAllFilms() {
        return service.getAllFilms();
    }
}
