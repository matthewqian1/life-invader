import logo from './logo.svg';
import Navbar from './NavBar';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import { BrowserRouter as Router, Route, Routes, BrowserRouter } from 'react-router-dom';
import AddCalories from './AddCalories';
import Message from './Message.tsx';
import ListGroup from './components/ListGroup.tsx';
import LineGraph from './components/LineGraph.tsx';
import React from 'react';
import Login from './Login';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route index element={<AddCalories/>}></Route>
          <Route index element={<Login/>} path='/login'></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
