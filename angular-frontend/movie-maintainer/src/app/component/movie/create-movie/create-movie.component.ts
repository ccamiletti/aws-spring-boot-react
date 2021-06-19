import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from 'src/app/model/movie';
import { MovieService } from 'src/app/service/movie.service';
import { FormControl, FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-create-movie',
  templateUrl: './create-movie.component.html',
  styleUrls: ['./create-movie.component.css']
})
export class CreateMovieComponent implements OnInit {

  movie: Movie = new Movie();
  form = FormGroup

  constructor(private movieService: MovieService, private router: Router, private formBuilder: FormBuilder) { 

  }
  submitted=true;
  ngOnInit(): void {
  }

  addMovie() {
    this.movieService.addMovie(this.movie).subscribe(response => {
      console.log(response);
    }, 
    error => console.log("error => ", error));
  }

  goToMovieList() {
    this.router.navigate(['movies']);
  }

  onSubmit() {
    this.submitted=true;
    console.log(this.movie)
    //this.addMovie();
    //this.goToMovieList();
  }


}
