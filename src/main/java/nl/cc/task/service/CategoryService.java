package nl.cc.task.service;

import nl.cc.task.entity.MovieEntity;
import nl.cc.task.model.MovieDTO;
import nl.cc.task.model.MovieResponse;
import nl.cc.task.repository.CategoryRepository;
import nl.cc.task.util.TmdbCategoryEnum;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    public final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    private MovieResponse mapToDTO(Page<MovieEntity> pageMovieEntity) {
        List<MovieDTO> movieDTOList = pageMovieEntity
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
        return new MovieResponse(movieDTOList, pageMovieEntity.getPageable().getPageNumber(), pageMovieEntity.getTotalPages(), pageMovieEntity.getTotalElements());
    }


    public MovieResponse getMoviesByCategory(TmdbCategoryEnum category, Pageable pageable) throws Exception {
        Page<MovieEntity> pageMovieEntity = categoryRepository.getMoviesByDescription(category.name(), pageable);
        MovieResponse movieResponse = mapToDTO(pageMovieEntity);
        return movieResponse;
    }
}
