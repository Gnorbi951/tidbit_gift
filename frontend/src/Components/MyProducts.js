import React, {useState, useEffect} from "react";
import Cards from "./Cards";
import axios from "axios";
import {Link} from "react-router-dom";
import styled from "styled-components";

const MyProducts =() => {

    const [products, setProducts] = useState();

    useEffect(() => {
        axios.get(`http://localhost:8762/productservice/product/user/${localStorage.getItem("id")}`)
            .then(response => {
                if (response.data[0]) {
                    setProducts(response.data)
                }
            });
    },[])

    return(
        <React.Fragment>
            {localStorage.getItem("id") ?
                <div>
                <NewProductButton>Upload new product <Link type="button" class="btn btn-warning" to={"/new-product"}>here!</Link></NewProductButton>
                    {products  ?
                        <Cards items={products} />
                        :
                        <StyledP>there are no products</StyledP>
                    }
                </div>
                :
                <p>Not logged in, please <Link to={"/login"}>Log in!</Link> </p>
            }
        </React.Fragment>
    )
}

const NewProductButton = styled.div`
    text-align: center;
    font-size: 1.3em;
    padding-top: 1em;
`

const StyledP = styled.p`
      text-align: center;
`

export default MyProducts;
