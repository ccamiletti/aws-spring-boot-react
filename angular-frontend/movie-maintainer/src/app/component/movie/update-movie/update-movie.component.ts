import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/service/movie.service';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/model/movie';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-update-movie',
  templateUrl: './update-movie.component.html',
  styleUrls: ['./update-movie.component.css']
})
export class UpdateMovieComponent implements OnInit {

  movie: Movie = new Movie();
  reactiveForm: FormGroup;
  submitted: boolean = false;
  constructor(private movieService: MovieService, private route: ActivatedRoute, private formBuilder: FormBuilder) { 
    this.reactiveForm = this.formBuilder.group({
      originalLanguage: new FormControl(null, [Validators.required])
    })
  }

  get f() {
    return this.reactiveForm.controls;
  }

  ngOnInit(): void {
    this.movieService.getMovieById(this.route.snapshot.params['id']).subscribe(response => {
      console.log("movie =>", response)
      this.movie = response;
    })
  }


  updateMovie() {
    this.submitted = true;
    console.log("movie updated =>", this.movie);
    if (this.reactiveForm.invalid) {
      return;
    }
    // this.movieService.updateMovie(this.movie, this.movie.id).subscribe(response => {
    //    console.log(response); 
    // })
  }

}
