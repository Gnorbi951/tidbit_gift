import React, {useEffect, useState} from "react";
import axios from "axios";

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
                        <h3 className="my-3">Project Description</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida
                            pellentesque urna varius vitae. Sed dui lorem, adipiscing in adipiscing et, interdum nec
                            metus. Mauris ultricies, justo eu convallis placerat, felis enim.</p>
                        <h3 className="my-3">Project Details</h3>
                        <ul>
                            <li>Lorem Ipsum</li>
                            <li>Dolor Sit Amet</li>
                            <li>Consectetur</li>
                            <li>Adipiscing Elit</li>
                        </ul>
                    </div>

                </div>
            </div> ) : <img src={"https://thumbs.gfycat.com/DearWellinformedDalmatian-size_restricted.gif"} //This is the loading image
                            alt={"Loading"} /> }
        </React.Fragment>
    )
}

export default ProductDetail;