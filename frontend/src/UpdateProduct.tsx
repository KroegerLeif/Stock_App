import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";

export default function UpdateProduct(){
    const { id } = useParams();
    const navigate = useNavigate();

    const [name , setName] = useState("");
    const [description , setDescription] = useState("")
    const [stock , setStock] = useState(0)
    const [price , setPrice] = useState(0)
    const [message , setMessage] = useState("")

    useEffect(() => {
        axios.get(`/api/product/${id}`)
            .then(res => {
                setName(res.data.name)
                setDescription(res.data.description)
                setStock(res.data.stock)
                setPrice(res.data.price)
            })
            .catch(err => console.error(err));
    }, [id]);

    function updateProduct(e: React.FormEvent) {
        e.preventDefault();
        axios.put(`/api/product/update/${id}`, {
            name: name,
            description: description,
            stock: stock,
            price: price
        })
            .then(() => {
                setMessage("Produkt erfolgreich aktualisiert")
                setTimeout(() => navigate("/"), 1500);
            })
            .catch(() =>
                setMessage("Fehler beim Aktualisieren"))
    }

    return (
        <div className="container">
            <h2>Edit Product</h2>
            <form onSubmit={updateProduct}>
                <label>Name
                    <input value={name} onChange={(e) => setName(e.target.value)} />
                </label>
                <label>Description
                    <input value={description} onChange={(e) => setDescription(e.target.value)} />
                </label>
                <label>Stock
                    <input value={stock} type="number" onChange={(e) => setStock(Number(e.target.value))} />
                </label>
                <label>Price
                    <input value={price} type="number" onChange={(e) => setPrice(Number(e.target.value))} />
                </label>

                <button>Save Changes</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );

}