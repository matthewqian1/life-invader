import { useState } from "react";
import { Route } from "react-router-dom";

function AddCalories(){
  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const blog = { title, body};
    console.log(blog);
    fetch('http://localhost:8080/account/ping' , {
      method: 'GET'
    }).then((result) => {
      console.log(result);
    })
  }

  return (
    <div className="create" style={{position: 'absolute', left: "800px"}}>
      <h2>Enter Food</h2>
      <form onSubmit={handleSubmit}>
        <label>Name:</label>
        <input 
          type="text" 
          required 
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
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