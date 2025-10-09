import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

interface Product {
    id: string;
    name: string;
    description: string;
    price: number;
}

function SearchBar(){

    const [searchValue, setSearchValue] = useState("");
    const [searchResults, setSearchResults] = useState<Product[]>([]);
    const nav = useNavigate()

    function submitSearchForm(event: React.FormEvent){
        event.preventDefault();
        axios.get("/api/products/search/"+searchValue)
            .then(response => {
                setSearchResults(response.data)
            })
            .catch(err => console.log(err));
    }

    function handleResultClick(id: string) {
        setSearchValue("");
        setSearchResults([]);
        nav("/products/" + id);
    }

    return (
        <div>
            <form className={"search-bar"} onSubmit={submitSearchForm}>
                    <input type={"text"}
                           placeholder={"Search for Product"}
                        value={searchValue}
                        onChange={event => setSearchValue(event.target.value)}
                    />
                    <button type={"submit"}>Search</button>
            </form>
            <div>
                {searchResults.map(product => (
                    <div key={product.id} onClick={() => handleResultClick(product.id)} style={{cursor: 'pointer', padding: '5px', borderBottom: '1px solid #ccc'}}>
                        <p>{product.name}</p>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default SearchBar;