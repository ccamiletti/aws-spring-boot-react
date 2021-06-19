package nl.cc.task.repository;

import nl.cc.task.entity.CategoryEntity;
import nl.cc.task.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByDescription(String categoryName);

    @Query("select Movie from CategoryEntity c inner join c.movies Movie where c.description = :description")
    Page<MovieEntity> getMoviesByDescription(@Param("description") String description, Pageable pageable);

}
