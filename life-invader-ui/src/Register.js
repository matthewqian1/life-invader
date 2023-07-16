import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Register(){
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const credentials = { username, password, email};
    fetch('http://localhost:8080/account/create' , {
      method: 'POST',
      headers: { "Content-Type": "application/json"},
      body: JSON.stringify(credentials)
    })
    .then(data => {
      if (data.status !== 200) {
        return data.text().then(text => { alert(text) });
      }
        console.log(data.body);
    })
  }

  return (
    <div className="create">
      <h2>Register</h2>
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
        <label>Email:</label>
        <input 
          type="text" 
          required 
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <button>Add</button>
      </form>
    </div>
  );
}
 
export default Register;