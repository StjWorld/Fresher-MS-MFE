
import { useEffect, useState } from "react";
import ProductService from "../services/ProductService";
import ProfileCard from "../components/ProfileCard";
import { AiOutlineShoppingCart } from "react-icons/ai";
import axios from "axios";
import Navbar from 'react-bootstrap/Navbar';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import Grid from '@mui/material/Unstable_Grid2'; // Grid version 2







const ProductPage = () => {
    let tempPrd = [];
    const [cart, setCart] = useState([]);
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [latestOrder , setLatestOrder] = useState();
    const [ordUp ,setOrdUp] = useState(false);
    const [localCart, setLocalCart] = useState([]);
    
    const id = 1;
    const [cartLen, setCartLen] = useState("");

    useEffect(() =>{
        const currentOrder = async () => {

            let response = await axios.get('http://127.0.0.1:3005/orders/1');
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

                    if (response.data[i].vis == true) {
                        console.log(response.data[i]);
                        tempPrd.push(response.data[i]);

                    }
                }
                setProducts(tempPrd);

                if(sessionStorage.getItem("cartItems")!=null){
                    setLocalCart(JSON.parse(sessionStorage.getItem("cartItems")));
                    console.log("loading cart session: " + localCart.length);
                }
                
            }catch(error) {
                console.log(error);
            }
            setLoading(false);
        };

        fetchData();

    }, []);
    const addNewOrder = (id, newOrder) => {
        axios.put(`http://127.0.0.1:3005/orders/${id}`, newOrder)
        .then(response => {console.log(localCart);})
        .catch(error => {
            console.error("Error updating data: ",error);
        });
        setOrdUp(true);
    };



    const handleClick = () => {

        let newOrder = latestOrder + 1;
        const updatedData = {
            order: newOrder
        };

        addNewOrder(id, updatedData);
    }

    const handleRedirect = async () => {
      
    };

    const handleAdd = (selection) => {

        let cartObj = {};
        cartObj = {
            orderId:latestOrder,
            id: ''+  selection.pid,
            pid:selection.pid,
            name:selection.pname,
            description: selection.pdesc,
            quantity: 1,
            inStock : selection.qty,
            unitPrice :selection.price,
            imageUrl: "http://localhost:4200/assets/images/" + selection.imgUrl
        };

        let boolVal = localCart.find((element) => {
            return element.pid === cartObj.pid;
          })

        if(boolVal == undefined){
        
        setLocalCart((localCart) => [
            ...localCart,
            cartObj,
        ]);
    }else{
        console.log("item is already in cart")
    }
        console.log(localCart);
        // code for adding product into previously sent orderId
    };
    useEffect(() => {

        if(localCart.length == 0){
        console.log("Updated cart is empty: ",localCart);
    }else{
        console.log("Cart is not empty  here are what inside it: ", localCart);

        setCartLen(localCart.length);

        sessionStorage.setItem("cartItems", JSON.stringify(localCart));
        
    }
    }, [localCart]);

    return (
        
        <div>
             <Navbar className="bg-body-tertiary justify-content-between">
      <Form inline>
       <h2>Product Catalog</h2>
      </Form>
      <Form inline>
        <Row>
          <Col xs="auto">
            <a href="/cart">
          <Button onClick={handleRedirect}><AiOutlineShoppingCart className='w-full h-full' />{cartLen}</Button>
          </a>
          </Col>
          <Col xs="auto">
            <Button onClick={handleClick}>Login</Button>
          </Col>
        </Row>
      </Form>
    </Navbar>
   

    <section>
      <Grid container spacing={2}>
                    {!loading && (
                      
                        products.map((displayObject) =>(
                             <Grid xs={4}  key={displayObject.pid}>
                           
                            <Card style={{ width: '18rem', display:"inline-block" }}>
                            <Card.Img variant="top" src={require('../assets/images/' + displayObject.imgUrl)} />
                            <Card.Body>
                            <Card.Title>  {displayObject.pname}</Card.Title>
                              <Card.Text>
                              {displayObject.pdesc}
                              </Card.Text>
                              <Card.Text>
                              ${displayObject.price}
                         
                              </Card.Text>
                
                              <Button  onClick={() => handleAdd(displayObject)} variant="primary">ADD TO CART</Button>
                        
                              </Card.Body>
                            </Card>
                            </Grid>
                    
                        ))
                    ) }
        </Grid>
    </section>
          
        </div>
        
    )

};

export default ProductPage;