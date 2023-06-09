import { useEffect, useState } from "react";

const useFetchRecipe = (url, categories) => 
{
    const [recipes, setRecipes] = useState(null);
    const [isPending, setIsPending] = useState(true)
    const [error, setError] = useState(null)
    
    const abortController = new AbortController();

    useEffect( () => {setTimeout(() => 
    {
        fetch(url, {method:"POST",
         mode:"cors",      
         headers: { 'Content-Type': 'application/json' },
         body: JSON.stringify({"categories":categories})
        },{signal:abortController.signal})
        .then(data => 
            { if(!data.ok)
                throw Error('Could not fetch data')
                return data.json()
            })
        .then(data => 
         {
                setRecipes(data);
                setIsPending(false)
                setError(null)
        }).catch(err =>
            {
            if(err.name='AbortError')
                console.log('Fetch Aborted')
            else
            {
                console.log(err.message)
                setIsPending(false)
                setError(err);
            }
        }
        )
        return () => abortController.abort();
    }, 0)}, [url, categories])

    return { recipes, isPending, error };
}
 
export default useFetchRecipe;