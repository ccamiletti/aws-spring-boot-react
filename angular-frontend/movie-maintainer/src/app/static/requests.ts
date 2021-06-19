const requests = {
    getTrending: 'api/movie/TRENDING',
    getNetflix: 'api/movie/NETFLIX',
    getTopRated: 'api/movie/TOP_RATED',
    getActionMovies: 'api/movie?withGenre=ACTION',
    getComedyMovies: 'api/movie?withGenre=COMEDY',
    getHorrorMovies: 'api/movie?withGenre=HORROR',
    getRomanceMovies: 'api/movie?withGenre=ROMANCE',
    getDocumenaries: 'api/movie?withGenre=DOCUMENTARY',
    getMovieById: 'api/movie/id',
    addMovie: 'api/movie',
    updateMovie: 'api/movie'
    
}

export default requests;