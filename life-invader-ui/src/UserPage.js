import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {useLocation} from 'react-router-dom';
import LineGraph from "./components/LineGraph.tsx";

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
        //console.log(consumptionSnapshot);
    })
  }, []);
  


  return (
    <div>
        Logged in as {username}
        <div>
          <LineGraph data={consumptionSnapshot}></LineGraph>
        </div>
    </div>
  );
}
 
export default UserPage;