import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import NavBar from "./Components/NavBar";
import MainPage from "./Components/MainPage";

function App() {
  return (
    <div className="App">
        <Router>
            <NavBar />
            <Route exact path={"/"} component={MainPage} />
        </Router>
    </div>
  );
}

export default App;
