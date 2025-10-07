import './App.css'
import {Route, Routes} from "react-router-dom";
import ViewAllProducts from "./ViewAllProducts.tsx";
import AddProduct from "./AddProduct.tsx";
import DetailProductPage from "./component/DetailProductPage.tsx";
import {useState} from "react";

function App() {

    const [uniqueProduct, setuniqueProduct] = useState({
        name: "",
        description: "",
        price: 0,
    });

  return (
    <>
        <Routes>
            <Route path={"/"} element={<ViewAllProducts/>}/>
            <Route path={"/add"} element={<AddProduct/>}/>
            <Route path={"/products/:id"} element={<DetailProductPage productName={uniqueProduct.name}
                                                                      description={uniqueProduct.description}
                                                                      productPrice={uniqueProduct.price}/>}/>
        </Routes>
    </>
  )
}

export default App
