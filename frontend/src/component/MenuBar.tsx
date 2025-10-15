import {Link} from "react-router-dom";
import SearchBar from "./SearchBar.tsx";

function MenuBar(){

    return(
        <div className={"menu-bar"}>
            <nav>
                <Link to={"/products"}>Warehouse Management</Link>
            </nav>
        </div>
    )
}

export default MenuBar;