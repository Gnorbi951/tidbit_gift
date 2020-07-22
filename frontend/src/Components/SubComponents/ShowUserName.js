import React, {useEffect, useState} from "react";
import axios from "axios";

const ShowUserName = props => {
    const [userData, setUserData] = useState();

    useEffect(() => {

        axios.get(`http://localhost:8762/userservice/user/${props.id}`)
            .then(resp => setUserData(resp));

    }, [props])

    return (
        <React.Fragment>
        {userData ? <p>{userData.data.name}</p> : <p>No data</p>}
        </React.Fragment>
    )
}

export default ShowUserName;