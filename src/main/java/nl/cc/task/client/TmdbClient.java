package nl.cc.task.client;

import lombok.extern.slf4j.Slf4j;
import nl.cc.task.exception.TmdbException;
import nl.cc.task.util.TmdbCategoryEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class TmdbClient {

    public final Logger logger = LoggerFactory.getLogger(TmdbClient.class);

    @Value("${genre-uri}")
    public String GENRE_URI;

    public static final String SERVICE_CALL_FAILED = "there was an error getting movies: %s";

    private final String apiKey;
    private final WebClient webClient;

    public TmdbClient(WebClient webClient, String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public TmdbResponse getByCategory(TmdbCategoryEnum filter) {
        return webClient.get()
                .uri(String.format(filter.uri(), this.apiKey))
                .exchangeToMono(this::handleResponse)
                .block();
    }

    @Cacheable(value = "genres")
    public TmdbGenreResponse getGenreList() {
        logger.info("**********getting genres**********");
        return webClient.get()
                .uri(String.format(GENRE_URI, this.apiKey))
                .retrieve()
                .bodyToMono(TmdbGenreResponse.class)
                .block();
    }

    public TmdbResponse getByGenreId(Long id) {
        return webClient.get()
                .uri(String.format(TmdbCategoryEnum.GENRE.uri(), this.apiKey, id))
                .retrieve()
                .bodyToMono(TmdbResponse.class)
                .block();
    }

    private Mono<TmdbResponse> handleResponse(ClientResponse clientResponse) {
        if (clientResponse.statusCode() == HttpStatus.OK) {
            return clientResponse.bodyToMono(TmdbResponse.class);
        }
        return Mono.error(new TmdbException(String.format(SERVICE_CALL_FAILED,
                clientResponse.statusCode().value()), HttpStatus.SERVICE_UNAVAILABLE));
    }


}
