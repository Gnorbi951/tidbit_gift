import React from "react";
import axios from "axios";

const NewProduct = () => {
    let product = {"id":"0","userId": `${localStorage.getItem("id")}`,"name": "", "description": "", "price": "", "picture": ""};


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
                console.log(response)}
            );
    }


        // fetch('http://localhost:8762/productservice/product', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: JSON.stringify(form)
        // })
        //     .then(response => response.json())
        //     .then(response => {
        //         console.log(response)
        //     });

return(
        <React.Fragment>
            <form className="form" action="" method="post">
                <h3>New Product</h3>
                    <label>Name:</label>
                    <input
                        type="text"
                        name="name"
                        id="name"
                    />
                    <br/>
                    <label>Description:</label>
                    <input
                        type="text"
                        name="description"
                        id="description"
                    />
                    <br/>
                    <label>Price:</label>
                    <input
                        type="number"
                        name="price"
                        id="price"
                    />
                    <br/>
                    <label>Picture:</label>
                    <input
                        type="text"
                        name="picture"
                        id="picture"
                    />
                    <br/>
                    <input
                        type="submit"
                        name="submit"
                        id="submit"
                        onClick={addNewProduct}
                    />
            </form>
        </React.Fragment>
    )
}

export default NewProduct;