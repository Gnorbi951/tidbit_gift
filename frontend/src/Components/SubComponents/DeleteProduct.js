import React, {useEffect, useState} from "react";
import axios from 'axios';
import {useHistory} from "react-router-dom";

const DeleteProduct = (props) => {
    const history = useHistory();

    const deleteProduct = () => {
        axios.delete(`http://localhost:8762/productservice/product/${props.id}`)
            .then((response) => {
                // if ((window.location.href).includes("my-products")) {
                //     history.push("/my-products")
                //     // window.location.reload();
                // } else {
                    history.push("/")
                // }
            })
    }

    return (
        <div>
            <button type="button" className="btn btn-dark" style={centeredButton} onClick={deleteProduct}>Delete Product
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