package nl.cc.task.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class TmdbGenreResponse {

    private final List<TmdbGenre> tmdbGenreList;

    @JsonCreator
    TmdbGenreResponse(@JsonProperty("genres") List<TmdbGenre> tmdbGenreList) {
        this.tmdbGenreList = tmdbGenreList;
    }

}
