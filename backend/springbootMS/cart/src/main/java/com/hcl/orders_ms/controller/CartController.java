package com.hcl.orders_ms.controller;

import com.hcl.orders_ms.config.CartWithProds;
import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.publisher.ProducerToOrder;
import com.hcl.orders_ms.publisher.RabbitMQProducerToProd;
import com.hcl.orders_ms.service.CartService;
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

    @Autowired
    private ProducerToOrder producerToOrder;

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
        return ResponseEntity.ok(cartWithProds + "are being sent to Producers");
    }

    @PostMapping("/sendToOrder")
    public ResponseEntity<String> sendToOrder(@RequestBody Cart cart){
        System.out.println("The cart: "+cart);
        producerToOrder.sendMessage(cart);
        return ResponseEntity.ok(cart + "are being sent to Order service");
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAll(){
       List<Cart> cart = cartService.getAll();
       return ResponseEntity.ok(cart);
    }



    @PostMapping
   public ResponseEntity<Cart> createCart(@RequestBody Cart cart){
        Cart createdCart = cartService.createCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
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
