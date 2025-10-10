import './App.css'
import {Route, Routes} from "react-router-dom";
import ViewAllProducts from "./ViewAllProducts.tsx";
import AddProduct from "./AddProduct.tsx";
import DetailProductPage from "./component/DetailProductPage.tsx";

import UpdateProduct from "./UpdateProduct.tsx";
import MenuBar from "./component/MenuBar.tsx";
function App() {


  return (
    <>
        <MenuBar/>
        <Routes>
            <Route path={"/"} element={<ViewAllProducts/>}/>
            <Route path={"/products/add"} element={<AddProduct/>}/>
            <Route path="/edit/:id" element={<UpdateProduct />} />
            <Route path={"/products/:id"} element={<DetailProductPage/>}/>
        </Routes>
    </>
  )
}

export default App
