import {useEffect, useState} from "react";
import axios from "axios";
import DeleteButton from "./DeleteButton.tsx";
import type {Product} from "./Product.ts";
import {Link} from "react-router-dom";
import {useNavigate} from "react-router-dom";
import SearchBar from "./component/SearchBar.tsx";

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
            <SearchBar/>
            <button onClick={() => navigate("/products/add")}>Produkt anlegen</button>
            <div className="container">
                <h1>Produkte Liste</h1>
                {productsList.length === 0 ? (
                    <p>Keine Produkte gefunden.</p>
                ) : (
                    <table>
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Lagerbestand</th>
                            <th>Preis €</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        {productsList.map((char) => (
                            <tr key={char.id}>
                                <td width={"30%"}>{char.name}</td>
                                <td>{char.stock}</td>
                                <td>{char.price} </td>
                                <td  align={"right"}><Link to={`/edit/${char.id}`}>
                                    <button>Bearbeiten</button>
                                </Link>
                                    <DeleteButton productId={char.id}
                                                  onDeleted={() =>
                                                      setProductsList(productsList
                                                          .filter(product => product.id !== char.id))}/>
                                </td>
                            </tr>
                        ))
                        }
                        </tbody>
                    </table>
                )}
            </div>
        </>
    );
}