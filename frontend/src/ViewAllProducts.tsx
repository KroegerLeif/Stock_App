import {useEffect, useState} from "react";
import  axios from "axios";
import type {Product} from "./Product.ts";
import {Link} from "react-router-dom";

export default function ViewAllProducts(){

    const [productsList, setProductsList] = useState<Product[]>([]);


    useEffect(() => {
        axios.get("/api/product")
            .then((res) => setProductsList(res.data))
            .catch((err) => console.error("Fehler beim Laden:", err));
    }, []);

    return (
        <div className="container">
            <h1>Produkte Liste</h1>
            {productsList.length === 0 ? (
                <p>Keine Produkte gefunden.</p>
            ) : (
                productsList.map((char) => (
                    <div key={char.id} className="product-item">
                        <h3>ID: {char.id}</h3>
                        <p><strong>Name:</strong> {char.name}</p>
                        <p><strong>Description:</strong> {char.description}</p>
                        <p><strong>Stock:</strong> {char.stock}</p>
                        <p><strong>Price:</strong> {char.price}</p>
                        <Link to={`/edit/${char.id}`}>
                            <button>Edit</button>
                        </Link>
                    </div>
                ))
            )}
        </div>
    );
}