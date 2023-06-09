import {BrowserRouter as Router, Route, Switch, useHistory} from 'react-router-dom'
import Navbar from './Navbar';
import Home from './Home'
import './css/index.css'
import useFetchRecipe from './useFetchRecipe';
import { useState } from 'react';

function App() {
  const [categories, setCategories] = useState([])

  const checkboxClicked = (e) =>
  {
      const isChecked = e.target.checked;
      if(isChecked)
      {
          categories.push(e.target.value)
          setCategories(categories)
      }
      else
      {
          var index = categories.indexOf(e.target.value)
          if (index !== -1) 
          {
              categories.splice(index, 1);
              setCategories(categories)
          }             
      }
    }


  return (
    <Router>
      
    <div className="App">
      <Switch>
        <Route path='/login'>
        </Route>
        <Route path='/register'>
        </Route>
        <Route path='*'>
          <Navbar categories={categories} checkboxClicked={(e) => checkboxClicked(e)}/>
        </Route>
      </Switch>

      <Switch>
        <Route path='/'>
          <Home categories={categories} />
        </Route>
      </Switch>
    </div>
    </Router>
  );
}

export default App;
