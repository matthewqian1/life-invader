import { useState } from "react";
import { Route } from "react-router-dom";

function Login(){
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const credentials = { username, password};
    fetch('http://localhost:8080/account/login' , {
      method: 'POST',
      headers: { "Content-Type": "application/json"},
      body: JSON.stringify(credentials)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
    })
  }

  return (
    <div className="create">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <label>Username:</label>
        <input 
          type="text" 
          required 
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <label>Password</label>
        <textarea
          required
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        ></textarea>
        <button>Add</button>
      </form>
    </div>
  );
}
 
export default Login;