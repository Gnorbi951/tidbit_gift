import React from "react";
import axios from 'axios';

const DeleteProduct = (props) => {

    const deleteProduct = () => {
        axios.delete(`http://localhost:8762/productservice/product/${props.id}/${props.userId}`)
            .then((response) => {
                console.log(response)
            })
    }

    return (
        <div>
            <button onClick={deleteProduct}>Delete Product</button>
        </div>
    )
}

export default DeleteProduct;