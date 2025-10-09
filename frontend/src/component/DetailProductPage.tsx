import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

function DetailProductPage(){

    const { id } = useParams();
    const [name,setName] = useState("")
    const [descripiton, setDescription] = useState("")
    const [price, setPrice] = useState(0)

    useEffect(()=> {
        axios.get(`/api/products/${id}`,)
            .then(res => {
                setName(res.data.name)
                setDescription(res.data.description)
                setPrice(res.data.price)
            })
            .catch(err => console.error(err));
    },[id])

    if(name === ""){
        return (

                <div className="detail-product-loading">
                    <h1>Loading...</h1>
                </div>

        )
    }
    else{
        return(

                <div className="detail-product">
                    <h1 className={"product-name"}>{name}</h1>
                    <p className={"product-description"}>{descripiton}</p>
                    <p className={"product-price"}>{price}</p>
                </div>

        )
    }

}

export default DetailProductPage;