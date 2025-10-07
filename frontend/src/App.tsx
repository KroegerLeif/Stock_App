import './App.css'
import {Route, Routes} from "react-router-dom";
import ViewAllProducts from "./ViewAllProducts.tsx";
import AddProduct from "./AddProduct.tsx";
function App() {


  return (
    <>
        <Routes>
            <Route path={"/"} element={<ViewAllProducts/>}/>
            <Route path={"/add"} element={<AddProduct/>}/>
        </Routes>
    </>
  )
}

export default App
