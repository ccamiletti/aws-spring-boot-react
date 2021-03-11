package nl.cc.task.util;

import nl.cc.task.exception.TmdbException;
import org.springframework.http.HttpStatus;

public enum TmdbCategoryEnum {

    TRENDING("/trending/all/day?api_key=%s"),
    NETFLIX("/discover/tv?api_key=%s&sort_by=popularity.desc&with_networks=213"),
    TOP_RATED("/movie/top_rated?api_key=%s"),
    GENRE("/discover/movie?api_key=%s&with_genres=%d");

    private String uri;

    TmdbCategoryEnum(String uri){
        this.uri = uri;
    }

    public String uri() {
        return this.uri;
    }

    public static final TmdbCategoryEnum of(String category) {
        try {
            return TmdbCategoryEnum.valueOf(category);
        }catch(Exception e) {
            throw new TmdbException("", HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
