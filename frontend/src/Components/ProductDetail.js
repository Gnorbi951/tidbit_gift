import React, {useEffect, useState} from "react";
import axios from "axios";
import ShowUserDetails from "./SubComponents/ShowUserDetails";

const ProductDetail = props => {
    console.log(props)
    const [product, setProduct] = useState();

    useEffect(() => {

        axios.get(`http://localhost:8762/productservice/product/${props.match.params.id}`)
            .then(response => setProduct(response.data))

    }, [props.match.params.id])

    return (
        <React.Fragment>
            {product ? (
            <div className="container">

                <h1 className="my-4">{product.name}</h1>

                <div className="row">

                    <div className="col-md-8">
                        <img className="img-fluid" src={product.picture} alt="" />
                    </div>

                    <div className="col-md-4">
                        <h3 className="my-3">Product Description</h3>
                        <p>{product.description}</p>
                        <h5>Price: {product.price} Huf</h5>
                        <h3 className="my-3">User Contact:</h3>
                        <ShowUserDetails id={product.userId} caller={"productDetail"} />
                    </div>

                </div>
            </div> ) : <img src={"https://thumbs.gfycat.com/DearWellinformedDalmatian-size_restricted.gif"} //This is the loading image
                            alt={"Loading"} /> }
        </React.Fragment>
    )
}

export default ProductDetail;