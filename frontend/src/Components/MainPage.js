import React, {useEffect, useState} from "react";
import axios from "axios";
import Cards from "./Cards";
import styled from "styled-components";

const MainPage = () => {

    const [items, setItems] = useState();

    useEffect(() => {
        axios.get("http://localhost:8762/productservice/product")
            .then(response => setItems(response.data));
    }, [])

    return(
        <React.Fragment>
            {items ? <Cards items={items} />
            : <LoadingImage src={"https://thumbs.gfycat.com/DearWellinformedDalmatian-size_restricted.gif"} //This is the loading image
               alt={"Loading"} />}
        </React.Fragment>
    )

}

export const LoadingImage = styled.img`
    display:block;
    margin-left:auto;
    margin-right:auto;
`;

export default MainPage;