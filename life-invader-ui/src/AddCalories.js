import { useState } from "react";
import {useLocation} from 'react-router-dom';
import NutritionDetails from "./NutritionDetails";

function AddCalories(){
  const location = useLocation();
  const [foodItem, setFoodItem] = useState('');
  const [body, setBody] = useState('');
  const [showNutritionDetails, setShowNutritionDetails] = useState(false);
  const [nutritionDetails, setNutritionDetails] = useState('');

  const searchFood = (e) => {
    e.preventDefault();
    fetch(`http://localhost:8080/nutrition/search/${foodItem}` , {
      method: 'GET',
      headers: { "Content-Type": "application/json"}
    }).then((result) => {
      if (result.status !== 200) {
        alert("invalid food item, check spelling and try again")
        setShowNutritionDetails(false)
      } else {
        result.json().then(data => {
          setNutritionDetails(data);
          setShowNutritionDetails(true);
        })
      }
      
    })
  }

  const handleSubmit = (e) => {
    e.preventDefault();
  }

  return (
    <div className="create" style={{position: 'absolute', left: "800px"}}>
      <h2>Enter Food</h2>
      <form onSubmit={handleSubmit}>
        <label>Name:</label>
        <input 
          type="text" 
          required 
          value={foodItem}
          onChange={(e) => setFoodItem(e.target.value)}
        />
        <button onClick={searchFood}>Search</button>
        {showNutritionDetails && <NutritionDetails data={nutritionDetails}/>}
        <label>Amount(grams):</label>
        <textarea
          required
          value={body}
          onChange={(e) => setBody(e.target.value)}
        ></textarea>
        <button>Add</button>
      </form>
    </div>
  );
}
 
export default AddCalories;