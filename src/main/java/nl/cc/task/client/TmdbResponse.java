package nl.cc.task.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TmdbResponse {

    public final List<TmdbMovie> tmdbMovieList;
    public final Integer page;
    public final Long totalResults;
    public final Long totalPages;

    @JsonCreator
    public TmdbResponse(@JsonProperty("results") List<TmdbMovie> tmdbMovieList,
                        @JsonProperty("page") Integer page,
                        @JsonProperty("total_results") Long totalResults,
                        @JsonProperty("total_pages") Long totalPages) {
        this.tmdbMovieList = tmdbMovieList;
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

}
