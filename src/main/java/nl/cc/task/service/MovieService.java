package nl.cc.task.service;


import nl.cc.task.client.TmdbClient;
import nl.cc.task.client.TmdbGenre;
import nl.cc.task.client.TmdbResponse;
import nl.cc.task.util.TmdbCategoryEnum;
import nl.cc.task.util.TmdbGenreEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final TmdbClient tmdbClient;

    public MovieService(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    public TmdbResponse getByCategory(TmdbCategoryEnum filter) {
        return tmdbClient.getByCategory(filter);
    }

    public TmdbResponse getByGenre(TmdbGenreEnum tmdbGenreEnum) {
        return getGenreList()
                .stream()
                .filter(g -> g.name.equalsIgnoreCase(tmdbGenreEnum.name()))
                .findFirst()
                .map(g -> tmdbClient.getByGenreId(g.id)).get();
    }

    public List<TmdbGenre> getGenreList() {
        List<TmdbGenre> t = tmdbClient.getGenreList().getTmdbGenreList();
        return t;
    }
}
