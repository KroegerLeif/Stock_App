function SearchBar(){

    return(
        <form className={"search-bar"}>
            <label>
                Searchbar
                <input type={"text"}/>
                <button type={"submit"}>Search</button>
            </label>
        </form>
    )
}

export default SearchBar;