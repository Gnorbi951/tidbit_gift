import React from "react";

const Cards = props => {
    console.log(props.items)

    return(
        <React.Fragment>

            <div className="card-columns">
                {props.items.map((item) =>
                    <div className="card" key={item.id}>
                        <div className="card-img-top">
                            <img src={item.picture} alt={"Product"} width={"100%"} height={"100%"}/>
                        </div>
                        <div className="card-body">
                            <h5 className="card-title">{item.name}</h5>
                            <p className="card-text">{item.description}</p>
                            <h6 className="card-text">Price: {item.price} Huf</h6>
                            <p className="card-text"><small className="text-muted">Seller: {item.userId}</small></p>
                        </div>
                    </div>
                )}
            </div>
        </React.Fragment>
    )
}

export default Cards;