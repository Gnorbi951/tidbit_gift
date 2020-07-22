import React, {useState, useEffect} from "react";
import Cards from "./Cards";
import axios from "axios";

const MyProducts =() => {

    const [products, setProducts] = useState(false);

    useEffect(() => {
        axios.get(`http://localhost:8762/productservice/product/user/${localStorage.getItem("id")}`)
            .then(response => setProducts(response.data));
    },[])

    return(
        <React.Fragment>
            {products ? <Cards items={products} />
                : <img src={"https://thumbs.gfycat.com/DearWellinformedDalmatian-size_restricted.gif"} //This is the loading image
                       alt={"Loading"} />}
        </React.Fragment>
    )
}

export default MyProducts;