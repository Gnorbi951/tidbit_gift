import React, {useState} from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const serverRequest = {"username": username, "password": password}

    const logIn = (event) => {
        event.stopPropagation()
        //TODO: When authentication works change the endpoint
        axios
            .post('http://localhost:8762/...', serverRequest,{
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then((response) => {
                if (response.data.status !== "WRONG"){
                    logUserIn(response.data.token,response.data.username,response.data.roles,response.data.id)
                }
                else {
                    alert("Wrong username or password")
                }
            });
    }

    const logUserIn = (token,username,roles,id) => {
        localStorage.setItem("token",token)
        localStorage.setItem("roles",roles)
        localStorage.setItem("id",id)
        localStorage.setItem("username",username);
        window.location.href = '/';
    };

    const logOut = () => {
        localStorage.clear();
        window.location.href = '/login';
    }

    return (
        <React.Fragment>
            <div id="login">
                <h3 className="text-center text-white pt-5">Login form</h3>
                <div className="container">
                    <div id="login-row" className="row justify-content-center align-items-center">
                        <div id="login-column" className="col-md-6">
                            <div id="login-box" className="col-md-12">
                                <form id="login-form" className="form">
                                    <h3 className="text-center text-info">Login</h3>
                                    <div className="form-group">
                                        <label htmlFor="username" className="text-info">Username:</label>
                                        <br />
                                        <input autoComplete="off" type="text" name="username" id="username" className="form-control"
                                               value = {username} onChange = {e => setUsername(e.target.value)}/>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="password" className="text-info">Password:</label>
                                        <br />
                                        <input type="password" name="password" id="password" className="form-control"
                                               value = {password} onChange = {e => setPassword(e.target.value)}/>
                                    </div>
                                    <div id="register-link" className="text-right">
                                        <Link to="/registration" className="text-info">
                                            Register here
                                        </Link>
                                    </div>
                                </form>
                                <button className="btn btn-info btn-md" onClick={logIn} >Login</button>
                                {localStorage.getItem("username")?(
                                    <button className="btn btn-info btn-md" onClick={logOut}>Logout</button>
                                ):(<div/>)}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
};

export default Login;