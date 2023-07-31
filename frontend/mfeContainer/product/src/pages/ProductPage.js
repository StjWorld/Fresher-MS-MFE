
import { useEffect, useState } from "react";
import ProductService from "../services/ProductService";
import Button from "../components/Button";
import ProfileCard from "../components/ProfileCard";
import { AiOutlinePlus, AiOutlineShoppingCart } from "react-icons/ai";
import axios from "axios";


const ProductPage = () => {
    let tempPrd = [];
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [latestOrder , setLatestOrder] = useState();
    const [ordUp ,setOrdUp] = useState(false);

    useEffect(() =>{
        const currentOrder = async () => {

            let response = await axios.get('http://127.0.0.1:3001/orders/1');
            setLatestOrder(response.data.order);

        }
        currentOrder();
        setOrdUp(false);
   },[ordUp]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await ProductService.getProducts();
                for (var i in response.data){

                    if (response.data[i].showProduct == true) {
                        tempPrd.push(response.data[i]);

                    }
                }
                setProducts(tempPrd);
                
            }catch(error) {
                console.log(error);
            }
            setLoading(false);
        };

        fetchData();

    }, []);
    const addNewOrder = (id, newOrder) => {
        axios.put(`http://127.0.0.1:3001/orders/${id}`, newOrder)
        .then(response => {console.log(response.data);})
        .catch(error => {
            console.error("Error updating data: ",error);
        });
        setOrdUp(true);
    };

    const handleClick = () => {
        const id = 1;
        let newOrder = latestOrder + 1;
        console.log(latestOrder + " " + newOrder);
        const updatedData = {
            order: newOrder
        };
        /*
            Send order Id to Cart MFE  
        */
        addNewOrder(id, updatedData);
    }

    const handleRedirect = () => {
        window.location.href = "http://www.yahoo.com";
    };

    const handleAdd = (id) => {
        
        console.log("Adding item id:" + id);
    };




    return (
        <div>
            <section className='hero bg-slate-600'>
                <div className='hero-body'>
                    <p className='title'>Product Catalog</p>
                </div>            
            <div className='mb-1'>
                <Button secondary rounded onClick={handleClick}>Login</Button>
                <div> <Button secondary rounded onClick={handleRedirect}><AiOutlineShoppingCart className='w-full h-full'/></Button></div>    
            </div>  
            </section>

            <div className='container'>
            
                <section className='section'>
                    {!loading && (
                    <div className='grid grid-cols-4'>
                        {products.map((displayObject) =>(
                           <div key={displayObject.id} >
                            <ProfileCard
                             title={displayObject.productName}
                             handle={displayObject.pricePerUnit}
                             image={displayObject.productImg}
                             description={displayObject.productDescription}
                            />
                            <div className="mb-2 block w-full rounded bg-primary px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
                            <Button primary rounded onClick={() => handleAdd(displayObject.id)}>+ ADD</Button>
                            </div>
                        </div>))}
                    </div>)
}
                </section>
            </div>
        </div>
    )

};

export default ProductPage;