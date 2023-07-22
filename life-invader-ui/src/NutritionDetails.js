import { useState } from "react";
import {useLocation} from 'react-router-dom';

function NutritionDetails( detailsJson ){

  if (detailsJson !== null) {
    console.log(detailsJson.data.name);
    return (
        <div>
          <h2>Showing nutrition details for - {detailsJson.data.name}</h2>
            <label>calories:               {detailsJson.data.calories}</label>
            <label>carbs(mg):               {detailsJson.data.carbohydratesTotalMg}</label>
            <label>cholesterol(mg) :        {detailsJson.data.cholesterolMg}</label>
            <label>fat saturated(mg) :        {detailsJson.data.fatSaturatedG}</label>
            <label>fat total(mg) :        {detailsJson.data.fatTotalG}</label>
            <label>protein(g) :        {detailsJson.data.proteinG}</label>
            <label>fiber(g) :        {detailsJson.data.fiberG}</label>
            <label>servingSize(g) :        {detailsJson.data.servingSizeG}</label>
            <label>sugar(g) :        {detailsJson.data.sugarG}</label>
        </div>
      );
  } else {
    return <div></div>
  }

  
}
 
export default NutritionDetails;