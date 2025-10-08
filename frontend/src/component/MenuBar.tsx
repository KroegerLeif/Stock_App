import {Link} from "react-router-dom";
import SearchBar from "./SearchBar.tsx";

function MenuBar(){

    return(
        <>
            <nav>
                <Link to={"/"}>Home</Link>
                <Link to={"/products/add"}>Add Product</Link>
                <Link to={"/"}>View All Products</Link>
            </nav>
            <SearchBar/>
        </>
    )
}

export default MenuBar;