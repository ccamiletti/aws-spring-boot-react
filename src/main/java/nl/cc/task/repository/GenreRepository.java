package nl.cc.task.repository;

import nl.cc.task.entity.GenreEntity;
import nl.cc.task.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity, Long> {

    Optional<GenreEntity> findByName(String name);

    @Query("select movie from GenreEntity c inner join c.movies movie where c.id = :genreId and movie.title like %:title%")
    Page<MovieEntity> getMoviesByGenreId(@Param("genreId") Long genreId, @Param("title") String title, Pageable pageable);

}
