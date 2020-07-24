import React, {useEffect, useState} from "react";
import axios from "axios";

const ShowUserDetails = props => {
    const [userData, setUserData] = useState();

    useEffect(() => {

        axios.get(`http://localhost:8762/userservice/user/${props.id}`)
            .then(resp => {setUserData(resp)
        // console.log(resp)
            });

    }, [props])

    if (props.caller === "card") {
        return (
            <React.Fragment>
                {userData ? <p>{userData.data.name}</p> : <p>No data</p>}
            </React.Fragment>
        )
    }

    if (props.caller === "productDetail") {
        return (
            <React.Fragment>
                {userData ? (<p>Seller: {userData.data.name}<br/>E-mail: {userData.data.email}</p>): (<p>No Data found</p>)}
            </React.Fragment>
        )
    }


}

export default ShowUserDetails;