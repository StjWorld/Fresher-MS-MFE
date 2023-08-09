package com.hcl.orders_ms.controller;

import com.hcl.orders_ms.config.CartWithProds;
import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.publisher.RabbitMQProducerToProd;
import com.hcl.orders_ms.service.CartService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    CartService cartService;
    
    @Autowired
    private RabbitMQProducerToProd producer;

    @PostMapping("/purchase")
    public ResponseEntity<String> sendMessage(@RequestBody Cart cart){
        System.out.println("The cart: "+cart);
        CartWithProds cartWithProds = new CartWithProds();
        HashMap<Long,Long> map = new HashMap<>();

        cartWithProds.setCartId(cart.getId());
        for(CartItem cartItem: cart.getCartItems()){
            map.put(cartItem.getProductId(), Long.valueOf(cartItem.getQuantity()));
        }
        cartWithProds.setProds(map);

        producer.sendMessage(cartWithProds);

        return ResponseEntity.ok(cartWithProds + " is being sent to Producers");
    }
    

    @GetMapping
    public ResponseEntity<List<Cart>> getAll(){
    	List<Cart> cart = cartService.getAll();
    	return ResponseEntity.ok(cart);
    }


    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart){

    	System.out.println("hello" + cart);
    	Cart toMake = new Cart();
    	JSONParser parse = new JSONParser();
    	cartService.createCart(cart);

        return new ResponseEntity<Cart>(new Cart(), HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id")Long id, @RequestBody String cart){
    	Cart toMake = new Cart();
    	JSONParser parse = new JSONParser();
    	try {
    		JSONObject thing = (JSONObject)parse.parse(cart);
        	List<JSONObject> inner = (List<JSONObject>) thing.get("cartItems");
        	
        	toMake = cartService.updateCart(id, inner);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return new ResponseEntity<Cart>(toMake, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id")Long id){
        Optional<Cart> cart = cartService.getCartById(id);
        if(!cart.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(cart.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

}


