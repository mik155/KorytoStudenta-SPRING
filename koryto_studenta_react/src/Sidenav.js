import { useState } from "react";
import { useHistory } from "react-router-dom";


const Sidenav = ({loggedIn, history, checkboxClicked}) => 
{


    const closeNav  = () => 
    {
            var array = document.getElementsByClassName("sidenav");
            for(var i = 0; i < array.length; i++)
            {
                array[i].style.width = "0";
            }
        
            var array = document.getElementsByClassName("side-menu-opt");
            for(var i = 0; i < array.length; i++)
            {
                array[i].style.display = "none";
            }
        
            document.getElementById("side-menu-form").style.display = "none";
    };
    
    const categoriesList = [  "PASTA", "BURGER", "PIZZA", "SPAGHETTI", "GEORGIAN"]
    return (        

    
    <div className="sidenav">
    <form action="search" id="side-menu-form" >
        <button id="side-nav-button" onClick={closeNav} type="button" >ZATWIERDŹ</button>
        <input type="text"  id="search" name="category-search" placeholder="SEARCH RECIPE"/>


        {categoriesList && categoriesList.map((cat) => (
                    <div className="side-menu-opt">
                        <label>{cat}</label>
                         <input type="checkbox" id="input" name="category-checkbox" value={cat} onChange={(e) => 
                         {
                            checkboxClicked(e, history);

                        }}/>
                    </div>
             ))
        }


    {
        loggedIn && 
        <div className="side-menu-opt">
             <label for="fav">FAVOURITES</label>
            <input type="checkbox" id="fav-input" name="category-checkbox" value="fav" checked="checked" onChange={checkboxClicked}/>
         </div>
    }

    </form>
</div>
);
}
 
export default Sidenav;