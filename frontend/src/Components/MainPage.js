import React, {useEffect, useState} from "react";
import axios from "axios";

const MainPage = () => {

    const [items, setItems] = useState();

    useEffect(() => {
        axios.get("http://localhost:8762/productservice/productservice/product")
            .then(response => console.log(response));

    })

    return(
        <React.Fragment>
            MainPage
        </React.Fragment>
    )

}

export default MainPage;