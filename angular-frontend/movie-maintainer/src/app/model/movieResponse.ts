import { Movie } from "./movie";

export class MovieResponse {

    results: Movie[];
    page: number;
    totalPages: number;
    totalResult: number;

}