import React, {useState, useEffect} from 'react'
import axios from 'axios'
import "./Row.css";
import Youtube from 'react-youtube';
import movieTrailer from 'movie-trailer'

function Row({title, fetchUrl, isLargeRow}) {

    const [movies, setMovies] = useState([]);
    const [trailerUrl, setTrailerUrl] = useState("");

    useEffect(() => {
        async function getMovies() {
            const response = await axios.get(fetchUrl);
            console.log(response.data.results)
            setMovies(response.data.results);
            return response;
        };
        getMovies();
    }, [fetchUrl]);

    const opts = {
        height: "390",
        width: "100%",
        aligns: "center",
        playerVars: {
            autoplay: 1,
        }
    }

    function handleClick(movie) {
        if (trailerUrl) {
            setTrailerUrl("");
        }else {
            console.log("movie?.name => ", movie?.name)
            console.log("movie?.title => ", movie?.title)
            movieTrailer(movie?.name || movie?.title || "").then((url) => {
                const urlParams = new URLSearchParams(new URL(url).search);
                setTrailerUrl(urlParams.get("v"))
            }).catch((error) => {
                console.log(error);
            })
        }
    }

    return (
        <div className="row">
            <h2>{title}</h2>
            <div className="row_posters">
                {movies.map(movie => (
                    movie.title,
                    <img key={movie.id} 
                        onClick={() => handleClick(movie)}
                        className={`row_poster ${isLargeRow && "row_posterLarge"}`} 
                        src={`${isLargeRow ? movie.poster_path : movie.backdrop_path}`} 
                        alt={movie.title}
                        title={movie.title || movie.name || movie.original_name}/>
                        
                ))}
            </div>
        
            {trailerUrl && <Youtube videoId={trailerUrl} opts={opts}/>}

        </div>

    )
}

export default Row
