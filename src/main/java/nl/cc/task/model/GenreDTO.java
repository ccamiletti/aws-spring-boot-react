package nl.cc.task.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {

    private Integer id;

    private String name;

    public GenreDTO(){}
    public GenreDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}