import {useEffect, useState} from "react";
import axios from "axios";
import DeleteButton from "./DeleteButton.tsx";
import type {Product} from "./Product.ts";
import {useNavigate} from "react-router-dom";

export default function ViewAllProducts() {

    const [productsList, setProductsList] = useState<Product[]>([]);

    const navigate = useNavigate();

    useEffect(() => {
        axios.get("/api/products")
            .then((res) => setProductsList(res.data))
            .catch((err) => console.error("Fehler beim Laden:", err));
    }, []);

    return (
        <>
            <button onClick={() => navigate("/products/add")}>Produkt anlegen</button>
            <div className="container">
                <h1>Produkte Liste</h1>
                {productsList.length === 0 ? (
                    <p>Keine Produkte gefunden.</p>
                ) : (
                    productsList.map((char) => (
                        <div key={char.id} className="todo-item">
                            <h3>ID: {char.id}</h3>
                            <p><strong>Name:</strong> {char.name}</p>
                            <p><strong>Description:</strong> {char.description}</p>
                            <p><strong>price:</strong> {char.price}</p>
                            <DeleteButton productId={char.id} onDeleted={() => setProductsList(productsList.filter(product => product.id !== char.id))} />
                        </div>
                    ))
                )}
            </div>
        </>
    );
}