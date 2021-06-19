package nl.cc.task.controller;

import nl.cc.task.model.GenreDTO;
import nl.cc.task.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/create")
    public void createGenreData() {
        genreService.createGenreData();
        //return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenreList() {
        return ResponseEntity.ok(genreService.findAll());
    }

}
