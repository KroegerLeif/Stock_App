import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";

export default function UpdateProduct() {
    const {id} = useParams();
    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [description, setDescription] = useState("")
    const [stock, setStock] = useState(0)
    const [price, setPrice] = useState(0)
    const [message, setMessage] = useState("")

    useEffect(() => {
        axios.get(`/api/products/${id}`)
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
        axios.put(`/api/products/update/${id}`, {
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
        <>
            <button onClick={() => navigate("/")}>Zurück</button>
            <div className="container">
                <h2>Produkt bearbeiten</h2>
                <form className={"product-form"} onSubmit={updateProduct}>
                    <label>Name:
                        <input value={name} onChange={(e) => setName(e.target.value)}/>
                    </label>
                    <label htmlFor="description">Beschreibung:
                        <textarea id="description" value={description} onChange={(e) => setDescription(e.target.value)}
                                  name={"description"} rows={4} cols={40}>
                    {description}
                </textarea>
                    </label>
                    <label>Lagerbestand:
                        <input value={stock} type="number" onChange={(e) => setStock(Number(e.target.value))}/>
                    </label>
                    <label>Preis:
                        <input value={price} type="number" onChange={(e) => setPrice(Number(e.target.value))}/>
                    </label>

                    <button>Änderungen speichern</button>
                </form>
                {message && <p>{message}</p>}
            </div>
        </>
    );

}