import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import NavBar from "./Components/NavBar";

function App() {
  return (
    <div className="App">
        <Router>
            <NavBar />
            <div>Hi there</div>
        </Router>
    </div>
  );
}

export default App;
