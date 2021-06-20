import React, {useState, useEffect} from 'react'
import './Nav.css'
import NetflixLogo from './images/netflixLogo.png';
import Account from './images/account.png';

function Nav() {

    const [show, handleShow] = useState(false);

    useEffect(() => {
        window.addEventListener("scroll", () => {
            if (window.scrollY > 100) {
                handleShow(true)
            } else handleShow(false) 
        })

    }, [])

    return (
        <div className={`nav ${show && "nav_black"}`}>
            <img className="nav_logo" src={NetflixLogo} alt = "Netflix"/>
            <img className="nav_avatar" src={Account} alt = ""/>
        </div>
    );

    
}

 export default Nav;
