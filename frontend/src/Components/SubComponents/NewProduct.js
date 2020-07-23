import React from "react";
import axios from "axios";
import {useHistory} from "react-router-dom";
import styled from "styled-components";

const NewProduct = () => {
    let product = {"id":"0","userId": `${localStorage.getItem("id")}`,"name": "", "description": "", "price": "", "picture": ""};
    const history = useHistory();

    const addNewProduct = (event) => {
        event.preventDefault();
        product.name = document.querySelector("#name").value;
        product.description = document.querySelector("#description").value;
        product.price = document.querySelector("#price").value;
        product.picture = document.querySelector("#picture").value;

        axios({
            method: 'post',
            url: 'http://localhost:8762/productservice/product',
            data: {
                id: product.id,
                userId: product.userId,
                name: product.name,
                description: product.description,
                price: product.price,
                picture: product.picture
            }
        })
            .then((response) => {
                    history.push("/my-products")
                    console.log(response)
                }
            );
    }

return(
        <React.Fragment>
            <div>
                <form style={centerForm} className="form" onSubmit={addNewProduct}>
                    <h3>New Product</h3>
                    <br/>
                    <label>Name:</label>
                    <input className="input-group-text"
                           type="text"
                            name="name"
                            id="name"
                            placeholder="name"
                           required
                        />
                    <br/>
                    <label>Description:</label>
                        <input className="input-group-text"
                            type="text"
                            name="description"
                            id="description"
                            placeholder="description"
                            required
                        />
                    <br/>
                    <label>Price:</label>
                        <input className="input-group-text"
                            type="number"
                            name="price"
                            id="price"
                            min="0"
                            required
                        />
                    <br/>
                    <label>Picture:</label>
                        <input className="input-group-text"
                            type="text"
                            name="picture"
                            id="picture"
                               placeholder="insert a valid url"
                        />
                        <p/>
                        <input
                            type="submit"
                            className="btn btn-dark"
                            name="submit"
                            id="submit"
                            value="Add product"
                        />
                </form>
            </div>
        </React.Fragment>
    )
}

const centerForm = {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "column"
}

export default NewProduct;