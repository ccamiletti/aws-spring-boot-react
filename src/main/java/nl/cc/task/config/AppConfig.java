package nl.cc.task.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import nl.cc.task.client.TmdbClient;
import nl.cc.task.client.TmdbGenre;
import nl.cc.task.service.GenreService;
import nl.cc.task.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class AppConfig {

    @Autowired
    public MovieService movieService;

    @Autowired
    public GenreService genreService;

    //@Bean
    public AmazonS3 amazonConfig(@Value("${aws-access-key}") String accessKey, @Value("${secret-access-key}") String secretAccessKey) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretAccessKey);
        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    @Bean
    public TmdbClient tmdbClient(@Value("${api-key}") String apiKey, @Value("${tmdb-url}") String tmdbUri) {
        WebClient webClient = WebClient.create(tmdbUri);
        return new TmdbClient(webClient, apiKey);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
