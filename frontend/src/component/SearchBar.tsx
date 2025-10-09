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
    const [searchResults, setSearchResults] = useState<Product[] | null>(null);
    const nav = useNavigate()

    function submitSearchForm(event: React.FormEvent){
        event.preventDefault();

        if (!searchValue.trim()) {
            setSearchResults(null);
            return;
        }

        axios.get("/api/products/search/"+searchValue)
            .then(response => {
                setSearchResults(response.data)
            })
            .catch(err => {
                console.log(err)
                setSearchResults([]);
            });
    }

    function handleResultClick(id: string) {
        setSearchValue("");
        setSearchResults(null);
        nav("/products/" + id);
    }

    return (
        <div className={"search-container"}>
            <form className={"search-bar"} onSubmit={submitSearchForm}>
                    <input type={"text"}
                           placeholder={"Search for Product"}
                        value={searchValue}
                        onChange={event => setSearchValue(event.target.value)}
                    />
                    <button type={"submit"}>Search</button>
            </form>
            {searchResults !== null && (
                <div className={"search-results"}>
                    {searchResults.length > 0 ? (
                        searchResults.map(product => (
                            <div key={product.id} onClick={() => handleResultClick(product.id)} style={{cursor: 'pointer', padding: '5px', borderBottom: '1px solid #ccc'}}>
                                <p>{product.name}</p>
                            </div>
                        ))
                    ) : (
                        <p>Nichts gefunden.</p>
                    )}
                </div>
            )}
        </div>
    )
}

export default SearchBar;