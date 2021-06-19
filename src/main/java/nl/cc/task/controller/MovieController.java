package nl.cc.task.controller;

import nl.cc.task.model.MovieDTO;
import nl.cc.task.model.MovieResponse;
import nl.cc.task.service.CategoryService;
import nl.cc.task.service.MovieService;
import nl.cc.task.util.TmdbCategoryEnum;
import nl.cc.task.util.TmdbGenreEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    public final MovieService movieService;
    public final CategoryService categoryService;

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body((movieService.getMovieById(id)));
    }

    @GetMapping("/{category}")
    public ResponseEntity<MovieResponse> getByCategory(@PathVariable("category") String category, @PageableDefault(page = 1, size = 10) Pageable pageable) throws Exception {
        TmdbCategoryEnum categoryEnum = TmdbCategoryEnum.valueOf(category);
        return ResponseEntity.ok().body(categoryService.getMoviesByCategory(categoryEnum, pageable));
    }

    @GetMapping("/admin")
    public ResponseEntity<MovieResponse> getMoviesByGenreAdmin(@RequestParam("withGenre") String genres,
                                                          @PageableDefault(page = 1, size = 10) Pageable pageable,
                                                          @RequestParam(name = "filterTitle", required=false) String filterTitle) {
        TmdbGenreEnum tmdbGenreEnum = TmdbGenreEnum.of(genres);
        MovieResponse movieResponse = movieService.getByGenreOld(tmdbGenreEnum, pageable, filterTitle);
        return ResponseEntity.ok().body(movieResponse);
    }

    @GetMapping
    public ResponseEntity<MovieResponse> getMoviesByGenre(@RequestParam("withGenre") String genres,
                                                          @PageableDefault(page = 0, size = 10) Pageable pageable,
                                                          @RequestParam(name = "title", required=false) String title) {
        TmdbGenreEnum tmdbGenreEnum = TmdbGenreEnum.of(genres);
        MovieResponse movieResponse = movieService.getByGenre(tmdbGenreEnum, pageable, title);
        return ResponseEntity.ok().body(movieResponse);
    }

    @PostMapping
    public ResponseEntity<String> saveMovie(@RequestBody MovieDTO movieDTO) {
        movieService.save(movieDTO);
        return ResponseEntity.ok().body("Movie saved correctly");
    }

    @GetMapping("/create")
    public void createMovieData() {
        movieService.getAndSavaAllMovies();
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{movieId}")
    public ResponseEntity updateMovie(@PathVariable("movieId") Long movieId, @RequestBody MovieDTO movieDTO) {
        movieService.update(movieDTO, movieId);
        return ResponseEntity.ok().build();
    }
}
