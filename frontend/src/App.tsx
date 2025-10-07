import './App.css'
import {Route, Routes} from "react-router-dom";
import ViewAllProducts from "./ViewAllProducts.tsx";
import AddProduct from "./AddProduct.tsx";
import UpdateProduct from "./UpdateProduct.tsx";
function App() {


  return (
    <>
        <Routes>
            <Route path={"/"} element={<ViewAllProducts/>}/>
            <Route path={"/add"} element={<AddProduct/>}/>
            <Route path="/edit/:id" element={<UpdateProduct />} />
        </Routes>
    </>
  )
}

export default App
