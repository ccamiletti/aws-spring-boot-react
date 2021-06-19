package nl.cc.task.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class TmdbGenre {

    private final Long id;
    private final String name;

    @JsonCreator
    TmdbGenre(@JsonProperty("id") Long id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

}
