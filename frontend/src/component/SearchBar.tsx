import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

function SearchBar(){

    const [searchValue, setSearchValue] = useState("");
    const [searchResult, setSearchResult] = useState({
        id: "",
        name: "",
        description: "",
        price: 0
    });
    const nav = useNavigate()

    function sumbitSearchForm(){
        axios.get("/api/products/search/"+searchValue)
            .then(response => setSearchResult(response.data))
            .catch(err => console.log(err));


    }

    useEffect(() => {
        setSearchValue("");
        nav("/products/" + searchResult.id)
    },[searchResult])

    return (
        <form className={"search-bar"} onSubmit={sumbitSearchForm}>
                <input type={"text"}
                       placeholder={"Search for Product"}
                    value={searchValue}
                    onChange={event => setSearchValue(event.target.value)}
                />
                <button type={"submit"}>Search</button>

        </form>
    )
}

export default SearchBar;