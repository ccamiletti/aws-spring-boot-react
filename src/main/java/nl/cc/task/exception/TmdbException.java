package nl.cc.task.exception;

import org.springframework.http.HttpStatus;

public class TmdbException extends RuntimeException {

    private final HttpStatus status;

    public TmdbException(Throwable throwable){
        this("Error processing data", throwable, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public TmdbException(String message, Throwable throwable){
        this(message, throwable, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public TmdbException(String message, HttpStatus status){
        this(message, null, status);
    }

    public TmdbException(String message, Throwable throwable, HttpStatus status){
        super(message, throwable);
        this.status = status;
    }

}
