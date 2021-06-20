const requests = {
    getTrending: '/movie/TRENDING',
    getNetflix: '/movie/NETFLIX',
    getTopRated: '/movie/TOP_RATED',
    getActionMovies: '/movie?withGenre=ACTION&filterTitle=""',
    getComedyMovies: '/movie?withGenre=COMEDY&filterTitle=""',
    getHorrorMovies: '/movie?withGenre=HORROR&filterTitle=""',
    getRomanceMovies: '/movie?withGenre=ROMANCE&filterTitle=""',
    getDocumenaries: '/movie?withGenre=DOCUMENTARY&filterTitle=""',
}

export default requests;