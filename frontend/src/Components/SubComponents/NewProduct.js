import React from "react";

const NewProduct = () => {
    let form = {"name": "", "description": "", "price": "", "picture": ""};

    const addNewProduct = () => {
        form.name = document.querySelector("#name").value;
        form.description = document.querySelector("#description").value;
        form.price = document.querySelector("#price").value;
        form.picture = document.querySelector("#picture").value;

        fetch('http://localhost:8762/productservice/product', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(form)
        })
            .then(response => response.json())
            .then(response => {
                console.log(response)
            });
    }

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