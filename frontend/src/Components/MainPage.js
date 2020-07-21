import React, {useEffect, useState} from "react";
import axios from "axios";
import Cards from "./Cards";

const MainPage = () => {

    const [items, setItems] = useState();

    useEffect(() => {
        axios.get("http://localhost:8762/productservice/product")
            .then(response => setItems(response.data));
    }, [])

    return(
        <React.Fragment>
            {items ? <Cards items={items} />
            : <img src={"https://thumbs.gfycat.com/DearWellinformedDalmatian-size_restricted.gif"} //This is the loading image
               alt={"Loading"} />}
        </React.Fragment>
    )

}

export default MainPage;