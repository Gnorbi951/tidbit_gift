import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import NavBar from "./Components/NavBar";
import MainPage from "./Components/MainPage";
import ProductDetail from "./Components/ProductDetail";
import Login from "./Components/Login";
import axios from "axios";
import MyProducts from "./Components/MyProducts";
import Registration from "./Components/Registration";

function App() {
    axios.interceptors.request.use(function (config) {
        const token = localStorage.getItem("token");
        config.headers.Authorization = `Bearer ${token}`;

        return config;
    });

    return (
    <div className="App">
        <Router>
            <NavBar />
            <Route exact path={"/"} component={MainPage} />
            <Route path={"/product/:id"} component={ProductDetail} />
            <Route exact path={"/login"} component={Login} />
            <Route exact path={"/my-products"} component={MyProducts} />
            <Route exact path={"/registration"} component={Registration} />
        </Router>
    </div>
    );
}

export default App;
