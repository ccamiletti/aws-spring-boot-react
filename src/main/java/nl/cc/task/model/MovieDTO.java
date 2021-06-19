package nl.cc.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieDTO {

    private Long id;
    private String originalLanguage;
    private String posterPath;
    private Boolean video;
    private String title;
    private String overview;
    private LocalDate releaseDate;
    private Long voteCount;
    private Boolean adult;
    private String backdropPath;
    private Double voteAverage;
    private String originalTitle;
    private Double popularity;
    private String mediaType;
    private String name;
    private String originalName;

    public MovieDTO() {
    }

    public MovieDTO(@JsonProperty("id") Long id,
                    @JsonProperty("original_language") String originalLanguage,
                    @JsonProperty("original_title") String originalTitle,
                    @JsonProperty("overview") String overview,
                    @JsonProperty("popularity") Double popularity,
                    @JsonProperty("poster_path") String posterPath,
                    @JsonProperty("release_date") LocalDate releaseDate,
                    @JsonProperty("title") String title,
                    @JsonProperty("video") Boolean video,
                    @JsonProperty("adult") Boolean adult,
                    @JsonProperty("vote_average") Double voteAverage,
                    @JsonProperty("vote_count") Long voteCount,
                    @JsonProperty("backdrop_path") String backdropPath,
                    @JsonProperty("media_type") String mediaType,
                    @JsonProperty("name") String name,
                    @JsonProperty("original_name") String originalName) {

        this.id = id;
        this.originalLanguage = originalLanguage;
        this.posterPath = posterPath;
        this.video = video;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteCount = voteCount;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
        this.originalTitle = originalTitle;
        this.popularity = popularity;
        this.mediaType = mediaType;
        this.name = name;
        this.originalName = originalName;
    }

}
