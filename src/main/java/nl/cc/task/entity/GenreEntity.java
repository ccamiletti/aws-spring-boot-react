package nl.cc.task.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
@Getter
@Setter
public class GenreEntity {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genreList")
    private Set<MovieEntity> movies = new HashSet<>();;



}