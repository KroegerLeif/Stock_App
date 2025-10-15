import {type ChangeEvent, type FormEvent, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import type {Product} from "./Product.ts";

export default function AddProduct() {

    const [product, setProduct] = useState<Product>({
        id: "",
        name: "",
        description: "",
        stock: 0,
        price: 0
    });

    const navigate = useNavigate();

    function postProduct(product: Product) {
        axios.post("/api/products", product)
            .then(() => {
                    navigate("/products")
                }
            )
            .catch((error) => console.log(error))
    }

    function handelSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        postProduct(product)
    }

    function handelInputChange(e: ChangeEvent<HTMLInputElement>) {
        const {name, value} = e.target;
        setProduct({
            ...product,
            [name]: value
        })
    }

    function handelTextAreaChange(e: ChangeEvent<HTMLTextAreaElement>) {
        const {name, value} = e.target;
        setProduct({
            ...product,
            [name]: value
        })
    }

    return (
        <>
            <button onClick={() => navigate("/products")}>Zurück</button>
            <div className="container">
                <h2>Neues Produkt anlegen</h2>
                <form className={"product-form"} onSubmit={handelSubmit}>
                    <label htmlFor="name">Name:</label>
                    <input id="name" value={product.name} onChange={handelInputChange} name={"name"} type="text"/>

                    <label htmlFor="description">Beschreibung:</label>
                    <textarea id="description" value={product.description} onChange={handelTextAreaChange}
                              name={"description"} rows={4} cols={40}>
                    {product.description}
                </textarea>

                    <label htmlFor="stock">Lagerbestand:</label>
                    <input id="stock" value={product.stock} onChange={handelInputChange} name={"stock"} type="number"/>

                    <label htmlFor="price">Preis:</label>
                    <input id="price" value={product.price} onChange={handelInputChange} name={"price"} type="number"/>

                    <button type="submit">Speichern</button>

                </form>
            </div>
        </>
    );
}