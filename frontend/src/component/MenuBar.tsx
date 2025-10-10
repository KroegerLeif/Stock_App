import {Link} from "react-router-dom";
import SearchBar from "./SearchBar.tsx";

function MenuBar(){

    return(
        <div className={"menu-bar"}>
            <nav>
                <Link to={"/"}>Warehouse Management</Link>
            </nav>
            <SearchBar/>
        </div>
    )
}

export default MenuBar;