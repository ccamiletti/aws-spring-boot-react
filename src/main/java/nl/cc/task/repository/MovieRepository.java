package nl.cc.task.repository;

import nl.cc.task.entity.GenreEntity;
import nl.cc.task.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {

    Page<MovieEntity> findByGenreListAndTitleContains(GenreEntity genreEntity, Pageable pageable, String title);
    Page<MovieEntity> findByTitleContains(Pageable pageable, String title);

}
