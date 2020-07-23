import React from "react";
import axios from "axios";
import {useHistory} from "react-router-dom";

const NewProduct = () => {
    let product = {"id":"0","userId": `${localStorage.getItem("id")}`,"name": "", "description": "", "price": "", "picture": ""};
    const history = useHistory()

    const addNewProduct = () => {
        product.name = document.querySelector("#name").value;
        product.description = document.querySelector("#description").value;
        product.price = document.querySelector("#price").value;
        product.picture = document.querySelector("#picture").value;

        console.log(product)

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
            <form style={centerForm} className="form">
                <h3>New Product</h3>
                    <label>Name:</label><br/>
                    <input
                        type="text"
                        name="name"
                        id="name"
                    />
                    <br/>
                    <label>Description:</label><br/>
                    <input
                        type="text"
                        name="description"
                        id="description"
                    />
                    <br/>
                    <label>Price:</label><br/>
                    <input
                        type="number"
                        name="price"
                        id="price"
                    />
                    <br/>
                    <label>Picture:</label><br/>
                    <input
                        type="text"
                        name="picture"
                        id="picture"
                    />
                    <br/>
                    <p/>
                    <input
                        type="button"
                        class="btn btn-dark"
                        name="submit"
                        id="submit"
                        value="Add product"
                        onClick={addNewProduct}
                    />
            </form>
        </React.Fragment>
    )
}

export default NewProduct;

const centerForm = {
    position: "absolute",
    left: "50%",
    top: "50%",
    transform: "translate(-50%, -50%)"
}
