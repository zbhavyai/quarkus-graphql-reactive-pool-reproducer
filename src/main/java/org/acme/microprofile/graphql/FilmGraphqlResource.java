package org.acme.microprofile.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import io.smallrye.mutiny.Uni;

@GraphQLApi
@ApplicationScoped
public class FilmGraphqlResource {

    @Inject
    FilmService service;

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public Uni<List<Film>> getAllFilms() {
        return service.getAllFilms();
    }
}
