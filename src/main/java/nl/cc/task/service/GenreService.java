package nl.cc.task.service;

import nl.cc.task.client.TmdbClient;
import nl.cc.task.client.TmdbGenre;
import nl.cc.task.entity.GenreEntity;
import nl.cc.task.model.GenreDTO;
import nl.cc.task.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GenreService {

    private final TmdbClient tmdbClient;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public GenreService(TmdbClient tmdbClient, GenreRepository genreRepository, ModelMapper modelMapper) {
        this.tmdbClient = tmdbClient;
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    private GenreEntity convertToEntity(TmdbGenre tmdbGenre) {
        return modelMapper.map(tmdbGenre, GenreEntity.class);
    }

    private GenreDTO convertToDto(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDTO.class);
    }

    public GenreEntity save(GenreEntity genreEntity) {
        return genreRepository.save(genreEntity);
    }

    public void createGenreData() {
        tmdbClient.getGenreList().getTmdbGenreList()
                .stream()
                .map(this::convertToEntity)
                .forEach(this::save);
    }

    public List<GenreDTO> findAll() {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        genreRepository.findAll().forEach(g -> {
            genreDTOList.add(convertToDto(g));
        });
        return genreDTOList;
    }
}
