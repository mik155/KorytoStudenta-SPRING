import RecipeList from "./RecipeList";
import useFetchRecipe from "./useFetchRecipe";


const Home = ({loggedIn, categories}) => 
{
    const {recipes, isPending, error} = useFetchRecipe('api/v1/recipe/load', categories);
    return (     
    <div className="home">
            {error && <div>{error.message}</div>}
            { isPending && <div>Loading ...</div>}
            { recipes && <RecipeList recipes= {recipes} /> }
    </div> );
}
 
export default Home;