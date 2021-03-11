package nl.cc.task.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class TmdbMovie {

    public final String id;
    public final String originalLanguage;
    public final String originalTitle;
    public final String overview;
    public final Long popularity;
    public final String posterPath;
    public final String releaseDate;
    public final String title;
    public final Boolean video;
    public final Boolean adult;
    public final Long voteAverage;
    public final Long voteCount;
    public final String backdropPath;
    public final String mediaType;
    public final String name;
    public final String originalName;

    public TmdbMovie(@JsonProperty("id") String id,
                     @JsonProperty("original_language") String originalLanguage,
                     @JsonProperty("original_title") String originalTitle,
                     @JsonProperty("overview") String overview,
                     @JsonProperty("popularity") Long popularity,
                     @JsonProperty("poster_path") String posterPath,
                     @JsonProperty("release_date") String releaseDate,
                     @JsonProperty("title") String title,
                     @JsonProperty("video") Boolean video,
                     @JsonProperty("adult") Boolean adult,
                     @JsonProperty("vote_average") Long voteAverage,
                     @JsonProperty("vote_count") Long voteCount,
                     @JsonProperty("backdrop_path") String backdropPath,
                     @JsonProperty("media_type") String mediaType,
                     @JsonProperty("name") String name,
                     @JsonProperty("original_name") String originalName) {
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.adult = adult;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.backdropPath = backdropPath;
        this.mediaType = mediaType;
        this.name = name;
        this.originalName = originalName;
    }

}
