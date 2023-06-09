import {useState} from 'react'
import {Link} from 'react-router-dom'
import './css/navbar.css'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBowlRice } from '@fortawesome/free-solid-svg-icons'
import Sidenav from './Sidenav'


const Navbar = ({categories, checkboxClicked, history}) => 
{
    const [loggedIn, setLoggedIn] = useState(true);
    const logout = () => {setLoggedIn(false); console.log("zmiana")};
    const openNav = () =>
     {
        var array = document.getElementsByClassName("sidenav");
        for(var i = 0; i < array.length; i++)
        {
            array[i].style.width = "30vw";
    
        }
    
        var array = document.getElementsByClassName("side-menu-opt");
        for(var i = 0; i < array.length; i++)
        {
            array[i].style.display = "flex";
        }
    
        document.getElementById("side-menu-form").style.display = "flex";
    }

    return (        
<div className="Navbar">
    <header>
        <Link to="/">
            <FontAwesomeIcon icon={faBowlRice}/>
            Koryto Studenta
        </Link>
    </header>
    <Sidenav loggedIn={true} categories={categories} checkboxClicked={(e)=>checkboxClicked(e)}/>
    <nav>
        <ul>
            <li>
                <button  onClick={openNav}>
                    KATEGORIE
                </button>
            </li>
            <li>
                <Link to="/regulamin">
                    REGULAMIN
                </Link>
            </li>
            
           {  loggedIn && 
            <li>
                <Link to="/addRecipe">
                    DODAJ PRZEPIS
                </Link>
            </li> }

            { loggedIn && <li>
                <button onClick={logout}>
                    WYLOGUJ
                </button>
                </li>
            }
            
            { !loggedIn && <li>
                <Link to="/login">
                    LOGOWANIE
                </Link>
            </li>}
             { !loggedIn && <li>
                <Link to="/register">
                    REJESTRACJA
                </Link>
            </li>}
        </ul>
    </nav>
</div>
);
}
 
export default Navbar;

       