import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateMovieComponent } from './component/movie/create-movie/create-movie.component';
import { MovieListComponent } from './component/movie/movie-list/movie-list.component';
import { UpdateMovieComponent } from './component/movie/update-movie/update-movie.component';

const routes: Routes = [
  {path: 'movies', component: MovieListComponent},
  {path: '', redirectTo: 'movies', pathMatch: 'full'},
  {path: 'create-movie', component: CreateMovieComponent},
  {path: 'update-movie/:id', component: UpdateMovieComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
