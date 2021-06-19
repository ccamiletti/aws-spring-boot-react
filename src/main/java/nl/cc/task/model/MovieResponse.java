package nl.cc.task.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.cc.task.client.TmdbMovie;

import java.util.List;

public class MovieResponse {

    public final List<MovieDTO> movieDTOList;
    public final Integer page;
    public final Integer totalPages;
    public final Long totalResult;

    @JsonCreator
    public MovieResponse(@JsonProperty("results") List<MovieDTO> movieDTOList,
                         @JsonProperty("page") Integer page,
                         @JsonProperty("totalPages") Integer totalPages,
                         @JsonProperty("totalResult") Long totalResult) {
        this.movieDTOList = movieDTOList;
        this.page = page;
        this.totalPages = totalPages;
        this.totalResult = totalResult;
    }


}
