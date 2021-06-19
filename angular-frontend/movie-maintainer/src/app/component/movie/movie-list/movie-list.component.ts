import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/model/movie';
import { MovieService } from 'src/app/service/movie.service';
import { Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies: Movie[];
  totalResult: number;
  page: number = 0;
  searchText = "";
  totalPages: number;
  pageEvent: PageEvent;

  constructor(private movieService: MovieService, private router: Router) { }

  ngOnInit(): void {
    this.getMovies(this.page);
  }

  public truncate(str: string) {
      return str?.length > 50 ? str.substr(0, 49) + '...' : str;
  }

  updateMovie(movieId: number) {
    this.router.navigate(['update-movie', movieId]);
  }

  getMovies(page: number) {
    this.movieService.getMovieList(page, this.searchText).subscribe(data => {
      this.totalResult = data.totalResult;
      this.page = data.page;
      this.totalPages = data.totalPages;
      this.movies = data.results;
    });
  }

  onPaginateChage(page: number) {
    this.getMovies(page);
  }

  searchByText() {
    this.getMovies(0);
  }

}
