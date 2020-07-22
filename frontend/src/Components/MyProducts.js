import React, {useState, useEffect} from "react";
import Cards from "./Cards";
import axios from "axios";
import {Link} from "react-router-dom";

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
                    {products  ?
                        <Cards items={products} />
                        :
                        <p>Upload new product <Link to={"/new-product"}>here!</Link></p>
                    }
                </div>
                :
                <p>Not logged in, please <Link to={"/login"}>Log in!</Link> </p>
            }
        </React.Fragment>
    )
}

export default MyProducts;
