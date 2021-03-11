package nl.cc.task.util;

import nl.cc.task.exception.TmdbException;
import org.springframework.http.HttpStatus;

public enum TmdbGenreEnum {

    ACTION, AVENTURE, ANIMATION, COMEDY, CRIME, DOCUMENTARY, DRAMA, FAMILY, HORROR, ROMANCE;

    public static TmdbGenreEnum of(String genres) {
        try{
            return TmdbGenreEnum.valueOf(genres);
        }catch(Exception e) {
            throw new TmdbException("", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
