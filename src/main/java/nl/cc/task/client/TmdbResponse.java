package nl.cc.task.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.util.List;

public class TmdbResponse {

    public final List<TmdbMovie> tmdbMovieList;
    public Integer page;

    @JsonCreator
    public TmdbResponse(@JsonProperty("results") List<TmdbMovie> tmdbMovieList, @JsonProperty("page") Integer page) {
        this.tmdbMovieList = tmdbMovieList;
        this.page = page;
    }


}
