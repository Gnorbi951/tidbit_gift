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
            <div className="container">

                <h1 className="my-4">Page Heading
                    <small>Secondary Text</small>
                </h1>

                <div className="row">

                    <div className="col-md-8">
                        <img className="img-fluid" src="http://placehold.it/750x500" alt="" />
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
            </div>
        </React.Fragment>
    )
}

export default ProductDetail;