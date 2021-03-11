package nl.cc.task.controller;


import nl.cc.task.client.TmdbResponse;
import nl.cc.task.service.MovieService;
import nl.cc.task.util.TmdbCategoryEnum;
import nl.cc.task.util.TmdbGenreEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    public final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{category}")
    public ResponseEntity<TmdbResponse> getAllMovies(@PathVariable("category") String category) {
        TmdbCategoryEnum filter = TmdbCategoryEnum.valueOf(category);
        return ResponseEntity.ok().body(movieService.getByCategory(filter));
    }

    @GetMapping
    public ResponseEntity<TmdbResponse> getMoviesByGenre(@RequestParam("withGenre") String genres) {
        TmdbGenreEnum tmdbGenreEnum = TmdbGenreEnum.of(genres);
        return ResponseEntity.ok().body(movieService.getByGenre(tmdbGenreEnum));
    }

}
