import {Link} from 'react-router-dom'


import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBriefcase, faHeartCircleMinus, faBell, faHeartCirclePlus,faHeart} from '@fortawesome/free-solid-svg-icons'
 


const  RecipeList = ({recipes}) => {
    console.log(recipes);
    console.log('refresh')

    return ( 
        <main>
        {
            recipes.map((recipe) => 
            (
                  <section class="recipes" key={recipe.id}>
          
                          <Link to = "">
                      <div class="recipe-1">
                          <img src={recipe.photoPath}/>
                          <div class="recipe-stat">
                              <div class="recipe-title"> 
                                  <h3>{recipe.name}</h3>
                              </div>
  
                              <div class="recipe-rate">
                                  <div class="recipe-rate-specific">
                                  <FontAwesomeIcon icon={faHeart} />
                                    <div id ="likes">
                                        {recipe.likes}
                                      </div>
                                  </div>
  
                                  <div class="recipe-rate-specific" id="prep_time">
                                  <FontAwesomeIcon icon={faBell} />
                                      {recipe.minutesToMake}
                                  </div>
  
                                  <div class="recipe-rate-specific" id="ingr_num">
                                  <FontAwesomeIcon icon={faBriefcase} />
                                      {recipe.ingriedientNumber}
                                  </div>
                              </div>
  
                          </div>
                          <div id="add-fav">
                                {recipe.fav && <FontAwesomeIcon icon={faHeartCircleMinus} />}
                                {!recipe.fav && <FontAwesomeIcon icon={faHeartCirclePlus} />}
                                
                          </div>
                      </div>
                    </Link>
        
                  </section>
            )

            )
        
        }

            </main>
             );
}
 
export default RecipeList;  