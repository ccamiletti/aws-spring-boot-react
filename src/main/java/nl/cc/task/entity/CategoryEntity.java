package nl.cc.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoryEntity {

    @Id
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "categorySet")
    private Set<MovieEntity> movies = new HashSet<>();

}
