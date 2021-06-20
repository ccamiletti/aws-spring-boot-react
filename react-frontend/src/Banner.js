import React, {useState, useEffect} from 'react'
import axios from 'axios'
import requests from './requests'
import './Banner.css'

function Banner() {

    const [movie, setMovie] = useState([]);

    useEffect(() => {
        async function getData() {
            const response = await axios.get(requests.getNetflix);
            setMovie(
                response.data.results[
                    Math.floor(Math.random() * response.data.results.length -1)
                ]
            );
            return response;
        };

        getData()
        
    }, []);

    function truncate(str, n) {
        return str?.length > n ? str.substr(0, n-1) + '...' : str;
    }

    return (
      <header className="banner"
        style={{
            backgroundSize: "cover",
            backgroundImage: `url("${movie?.backdrop_path}")`,
            backgroundPosition: "center center",
            }}
        >  
        <div className="banner_content">
            <h1 className="banner_title">{movie?.title || movie?.name || movie?.original_name}</h1>

            <div className="banner_buttons">
                <button className="banner_button">Play</button>
                <button className="banner_button">My List</button>

            </div>

            <h1 className="banner_description">
                {truncate(movie?.overview, 150)}
            </h1>
        </div>
        <div className="banner_fadeBottom"/>


      </header>  
    )
}

export default Banner
