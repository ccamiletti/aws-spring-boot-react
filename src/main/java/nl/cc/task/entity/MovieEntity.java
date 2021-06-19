package nl.cc.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@ToString
public class MovieEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "video")
    private Boolean video;

    @Column(name = "title")
    private String title;

    @Column(name = "overview")
    private String overview;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "vote_count")
    private Long voteCount;

    @Column(name = "adult")
    private Boolean adult;

    @Column(name = "backdrop_path")
    private String backdropPath;

    @Column(name = "vote_average")
    private Double voteAverage;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "name")
    private String name;

    @Column(name = "popularity")
    private Double popularity;

    @Column(name = "media_type")
    private String mediaType;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = true)
    )
    private Set<GenreEntity> genreList = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "movie_category", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns =  @JoinColumn(name = "category_id", nullable = true))
    private Set<CategoryEntity> categorySet = new HashSet<>();

}
