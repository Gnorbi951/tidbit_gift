import React from "react";
import { Link } from "react-router-dom";
import ShowUserName from "./SubComponents/ShowUserName";

const Cards = props => {
    return(
        <React.Fragment>

            <div className="card-columns">
                {props.items.map((item) =>
                    <div className="card" key={item.id}>
                        <div className="card-img-top">
                            <Link to={"/product/"+item.id}>
                                <img src={item.picture} alt={"Product"} width={"100%"} height={"100%"}/>
                            </Link>
                        </div>
                        <div className="card-body">
                            <h5 className="card-title">{item.name}</h5>
                            <p className="card-text">{item.description}</p>
                            <h6 className="card-text">Price: {item.price} Huf</h6>
                            <div className="card-text"><ShowUserName id={item.userId} /></div>
                        </div>
                    </div>
                )}
            </div>
        </React.Fragment>
    )
}

export default Cards;