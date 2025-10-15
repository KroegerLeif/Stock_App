import './App.css'
import {Route, Routes} from "react-router-dom";
import ViewAllProducts from "./ViewAllProducts.tsx";
import AddProduct from "./AddProduct.tsx";
import DetailProductPage from "./component/DetailProductPage.tsx";

import UpdateProduct from "./UpdateProduct.tsx";
import MenuBar from "./component/MenuBar.tsx";
import {useEffect, useState} from "react";
import axios from "axios";
import ProtectedRoutes from "./ProtectedRoutes.tsx";

function App() {

    const [user, setUser] = useState<string | null | undefined>(undefined)

    function login() {
        const host: string = window.location.host === "localhost:5173" ?
            "http://localhost:8080" : window.location.origin;
        window.open(host + '/oauth2/authorization/github', '_self');
    }

    function logout() {
        const host: string = window.location.host === "localhost:5173" ?
            "http://localhost:8080" : window.location.origin;
        window.open(host + '/logout', '_self');
    }

    const loadUser = () => {
        axios.get('/api/auth/me')
            .then(response => {
                setUser(response.data)
            })
            .catch(() => setUser(null))
    }

    useEffect(() => {
        loadUser();
    }, []);

    return (
        <>
            <MenuBar/>
            <div className={"d-flex-right"}>
            {!user ?
                <button className={"login-btn"} onClick={login}>Login</button>
                :
                <button className={"login-btn"} onClick={logout}>Logout</button>
            }
            </div>
            <Routes>
                <Route path={"/"} />
                <Route element={<ProtectedRoutes user={user}/>}>
                    <Route path={"/products"} element={<ViewAllProducts/>}/>
                    <Route path={"/products/add"} element={<AddProduct/>}/>
                    <Route path="/edit/:id" element={<UpdateProduct/>}/>
                    <Route path={"/products/:id"} element={<DetailProductPage/>}/>
                </Route>
            </Routes>
        </>
    )
}

export default App
