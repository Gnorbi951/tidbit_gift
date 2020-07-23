import React from "react";
import axios from 'axios';
import {useHistory} from "react-router-dom";

const DeleteProduct = (props) => {
    const history = useHistory()

    const deleteProduct = () => {
        axios.delete(`http://localhost:8762/productservice/product/${props.id}`)
            .then((response) => {
                history.push("/")
                console.log(response)
            })
    }

    return (
        <div>
            <button type="button" class="btn btn-dark" style={centeredButton} onClick={deleteProduct}>Delete Product
            </button>
        </div>
    )
}

export default DeleteProduct;

const centeredButton = {
    display: "block",
    marginLeft: "auto",
    marginRight: "auto",
    marginBottom: "1%"
 }