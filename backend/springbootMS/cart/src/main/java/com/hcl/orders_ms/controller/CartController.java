package com.hcl.orders_ms.controller;

import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    CartService cartService;

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
