import './App.css'
import {Route, Routes} from "react-router-dom";
import ViewAllProducts from "./ViewAllProducts.tsx";
import AddProduct from "./AddProduct.tsx";
import DetailProductPage from "./component/DetailProductPage.tsx";

function App() {


  return (
    <>
        <Routes>
            <Route path={"/"} element={<ViewAllProducts/>}/>
            <Route path={"/add"} element={<AddProduct/>}/>
            <Route path={"/products/:id"} element={<DetailProductPage/>}/>
        </Routes>
    </>
  )
}

export default App
