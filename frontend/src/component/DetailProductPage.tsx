import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

type productPromp = {
    productName: string;
    description: string;
    productPrice: number;
}

function DetailProductPage(){

    const id = useParams();
    const [product,setProduct] = useState<productPromp>();

    useEffect(()=>{
        axios.get(`/api/products/${id}`)
            .then(response => setProduct(response.data))
            .catch(err => console.log(err));
    },[id])

    if(!product){
        return (

                <div className="detail-product-loading">
                    <h1>Loading...</h1>
                </div>

        )
    }
    else{
        return(

                <div className="detail-product">
                    <h1 className={"product-name"}>{product.productName}</h1>
                    <p className={"product-description"}>{product.description}</p>
                    <p className={"product-price"}>{product.productPrice}</p>
                </div>

        )
    }

}

export default DetailProductPage;