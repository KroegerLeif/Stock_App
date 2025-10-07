type productPromp = {
    productName: string;
    description: string;
    productPrice: number;
}

function DetailProductPage(promp:Readonly<productPromp>){

    return(
        <>
            <div className="detail-product">
                <h1>{promp.productName}</h1>
                <p>{promp.description}</p>
                <p>{promp.productPrice}</p>
            </div>
        </>
    )
}

export default DetailProductPage;