package nl.cc.task.service;


import nl.cc.task.client.TmdbClient;
import nl.cc.task.client.TmdbGenre;
import nl.cc.task.client.TmdbMovie;
import nl.cc.task.client.TmdbResponse;
import nl.cc.task.entity.CategoryEntity;
import nl.cc.task.entity.GenreEntity;
import nl.cc.task.entity.MovieEntity;
import nl.cc.task.exception.TmdbException;
import nl.cc.task.model.MovieDTO;
import nl.cc.task.model.MovieResponse;
import nl.cc.task.repository.CategoryRepository;
import nl.cc.task.repository.GenreRepository;
import nl.cc.task.repository.MovieRepository;
import nl.cc.task.util.TmdbCategoryEnum;
import nl.cc.task.util.TmdbGenreEnum;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class MovieService {

    private final TmdbClient tmdbClient;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public MovieService(TmdbClient tmdbClient, MovieRepository movieRepository, ModelMapper modelMapper, GenreRepository genreRepository, CategoryRepository categoryRepository) {
        this.tmdbClient = tmdbClient;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
        this.categoryRepository = categoryRepository;
    }

    public TmdbResponse getByCategoryFromTmdb(TmdbCategoryEnum filter) {
        return tmdbClient.getByCategory(filter);
    }

    public MovieResponse getByGenreOld(TmdbGenreEnum tmdbGenreEnum, Pageable pageable, String filterTitle) {
        return genreRepository.findByName(tmdbGenreEnum.name())
                .map(g -> movieRepository.findByTitleContains(pageable, filterTitle))
                .map(this::convertEntityToDto)
                .orElse(new MovieResponse(List.of(), 0, 0, 0l));
    }

    public MovieResponse getByGenre(TmdbGenreEnum tmdbGenreEnum, Pageable pageable, String title) {
        System.out.print("genre => " + genreRepository.findByName(tmdbGenreEnum.name()).get().getId());
        return genreRepository.findByName(tmdbGenreEnum.name())
                .map(g -> genreRepository.getMoviesByGenreId(g.getId(), title, pageable))
                .map(this::convertEntityToDto)
                .orElse(new MovieResponse(List.of(), 0, 0, 0l));
    }

    public List<TmdbGenre> getGenreList() {

        return tmdbClient.getGenreList().getTmdbGenreList();
    }

    public void save(MovieDTO movieDTO) {
        movieRepository.save(convertToEntity(movieDTO));
    }

    public void update(MovieDTO movieDTO, Long id) {
        movieRepository.findById(id).map(movieEntity -> {
            mergeMovieForUpdate(movieDTO, movieEntity);
            return movieRepository.save(movieEntity);
        }).orElseThrow(() -> new TmdbException(String.format("Movie with ID %d not found", id), HttpStatus.NOT_FOUND));
    }

    private void mergeMovieForUpdate(MovieDTO movieDTO, MovieEntity movieEntity) {
        movieEntity.setAdult(movieDTO.getAdult());
        movieEntity.setName(movieDTO.getName());
        movieEntity.setOriginalName(movieDTO.getOriginalName());
        movieEntity.setBackdropPath(movieDTO.getBackdropPath());
        movieEntity.setMediaType(movieDTO.getMediaType());
        movieEntity.setOriginalLanguage(movieDTO.getOriginalLanguage());
        movieEntity.setOverview(movieDTO.getOverview());
        movieEntity.setPopularity(movieDTO.getPopularity());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setVideo(movieDTO.getVideo());
        movieEntity.setPosterPath(movieDTO.getPosterPath());
        movieEntity.setOriginalTitle(movieDTO.getOriginalTitle());
        movieEntity.setReleaseDate(movieDTO.getReleaseDate());
        movieEntity.setVoteAverage(movieDTO.getVoteAverage());
        movieEntity.setVoteCount(movieDTO.getVoteCount());
    }

    @Transactional
    public MovieEntity save(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    private MovieEntity convertToEntity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, MovieEntity.class);
    }
    private MovieDTO getMovieDTO(MovieEntity movieEntity) {
        return modelMapper.map(movieEntity, MovieDTO.class);
    }

    private MovieResponse convertEntityToDto(Page<MovieEntity> pageMovieEntity) {

        List<MovieDTO> movieDTOList = pageMovieEntity
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());

        return new MovieResponse(movieDTOList, pageMovieEntity.getPageable().getPageNumber(), pageMovieEntity.getTotalPages(), pageMovieEntity.getTotalElements());
    }

    public MovieEntity convertToEntity(TmdbMovie tmdbMovieDTO) {
        Set<GenreEntity> genreList = new HashSet<>();
        if (tmdbMovieDTO.genreIds != null) {
            tmdbMovieDTO.genreIds.stream().forEach(g -> {
                genreRepository.findById(g).map(genreList::add);
            });
        }
        MovieEntity movieEntity = modelMapper.map(tmdbMovieDTO, MovieEntity.class);
        movieEntity.setGenreList(genreList);
        return movieEntity;
    }

    public void getAndSavaAllMovies() {
        TmdbCategoryEnum.stream().forEach(cat -> {
            CategoryEntity categoryEntity = categoryRepository.findByDescription(cat.name()).orElse(null);
            getAndSavaAllMoviesByCategory(cat, categoryEntity);
        });
    }
    private void getAndSavaAllMoviesByCategory(TmdbCategoryEnum category, CategoryEntity categoryEntity) {
        TmdbResponse allMoviesByPage = tmdbClient.getAllMoviesByPage(1L, category);
        LongStream.range(1, allMoviesByPage.totalPages).forEach(page -> {
            List<MovieEntity> movieTmdbList = getMoviesFromTmdbByCategory(category, page);
            movieTmdbList.forEach(movieTmdb -> {
                movieRepository.findById(movieTmdb.getId()).ifPresentOrElse((movieEntity) -> {
                    movieEntity.getCategorySet().add(categoryEntity);
                    this.save(movieEntity);
                }, () -> {
                    movieTmdb.getCategorySet().add(categoryEntity);
                    this.save(movieTmdb);
                });
            });
        });
    }

    private List<MovieEntity> getMoviesFromTmdbByCategory(TmdbCategoryEnum category, Long page) {
        return tmdbClient.getAllMoviesByPage(page, category).tmdbMovieList
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }


    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public MovieDTO getMovieById(Long id) {
        return movieRepository.findById(id).map(this::getMovieDTO).orElse(null);
    }
}
