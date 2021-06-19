import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie';
import { MovieResponse } from '../model/movieResponse';
import requests from '../static/requests' 

@Injectable({
  providedIn: 'root'
})
export class MovieService { 

  constructor(private httpClient: HttpClient) {

  }

  getMovieList(page: number, title: string): Observable<MovieResponse> {
    return this.httpClient.get<MovieResponse>(requests.getActionMovies+'&page='+page+"&title="+title);
  }

  addMovie(movie: Movie): Observable<Object> {
    return this.httpClient.post(requests.addMovie, movie);
  }

  getMovieById(movieId: number): Observable<Movie> {
    return this.httpClient.get<Movie>(`${requests.getMovieById}/${movieId}`);
  }

  updateMovie(movie: Movie, movieId: number): Observable<any> {
    return this.httpClient.put<any>(`${requests.updateMovie}/${movieId}`, movie);
  }

}
