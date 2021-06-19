package nl.cc.task.util;

public enum TmdbType {

    TV("tv"),
    MOVIE("movie");

    private String type;

    TmdbType(String type){
        this.type = type;
    }

    public String type() {
        return this.type;
    }

}
