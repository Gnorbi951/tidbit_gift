import React, {useEffect, useState} from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSignInAlt } from "@fortawesome/free-solid-svg-icons";


const NavBar = () => {

    const [userName, setUserName] = useState("");

    useEffect(() => {

        setUserName(localStorage.getItem("username"));

    }, [])

    return (
        <React.Fragment>
            <NavBarHeader>
                <MyLink to={"/"}>Home</MyLink>
                {userName ? <UserHeader>Logged in as: {userName}</UserHeader> : <h4>Not Logged In</h4>}
                <LoginIcon to={"/login"}>
                    <FontAwesomeIcon icon={faSignInAlt} />
                </LoginIcon>
            </NavBarHeader>
        </React.Fragment>
    );
};

const NavBarHeader = styled.header`
  padding: 1rem;
  background-color: #2b2b2b;
  display: flex;
  width: 99.9vw;
  flex-direction: row;
  /* justify-content: flex-begin; */
  margin: 0;
`;
const MyLink = styled(Link)`
  margin: 0.2rem 1rem;
  color: #ffffff;
  text-decoration: none;
  text-transform: uppercase;
  font-weight: bold;
  &:hover {
    transition: 350ms;
    color: #a9a9a9;
    text-decoration: none;
  }
  font-size: 1.5rem;
`;

const UserHeader = styled.p`
margin: 0.2rem 1rem;
  color: #ffffff;
  text-decoration: none;
  text-transform: uppercase;
  font-weight: bold;
  &:hover {
    transition: 350ms;
    color: #a9a9a9;
    text-decoration: none;
  }
  font-size: 1.5rem;
`;

const LoginIcon = styled(Link)`
  font-size: 1.8rem;
  color: #ffffff;
  margin-left: auto;
  padding-right: 1rem;
  &:hover {
    transition: 350ms;
    color: #a9a9a9;
    text-decoration: none;
  }
`;

export default NavBar;