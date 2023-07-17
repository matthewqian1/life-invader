import { useState, useEffect } from "react";
import {useLocation} from 'react-router-dom';
import './App.css';
import LineGraph from "./components/LineGraph.tsx";
import AddCalories from "./AddCalories";

function UserPage(){
  const location = useLocation();
  const [username, setUsername] = useState('');
  const [consumptionSnapshot, SetConsumptionSnapshot] = useState([]);
  useEffect(() => {
    fetch('http://localhost:8080/account/account' , {
      method: 'GET',
      headers: { "Content-Type": "application/json", "Authorization": `${location.state.token}`}
    })
    .then(res => res.json()) 
    .then(data => {
        setUsername(data.username);
    })
  }, []);

  useEffect(() => {
    fetch('http://localhost:8080/nutrition/consumption/snapshot' , {
      method: 'GET',
      headers: { "Content-Type": "application/json", "Authorization": `${location.state.token}`}
    })
    .then(res => res.json()) 
    .then(data => {
        SetConsumptionSnapshot(data.history);
    })
  }, []);
  


  return (
    <>
    <div>
      <div class="fst-italic" style={{fontSize: '20px'}}>
      Logged in as {username}
      
</div>
<img className="profilePhoto" src={require("./img/Doggo.jpg")} alt="profile pic" ></img>
<div className='heading1'>
                <h1>1 week consumption</h1>
            </div>
<div className="sideBySide">
  <LineGraph data={consumptionSnapshot} style></LineGraph>
  
  <AddCalories></AddCalories>

</div>

    
    </div>
    </>
  );
}
 
export default UserPage;