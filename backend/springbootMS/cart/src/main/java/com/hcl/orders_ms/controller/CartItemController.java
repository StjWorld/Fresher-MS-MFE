package com.hcl.orders_ms.controller;




import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getAll(){
        List<CartItem> cartItems = cartItemService.getAll();
        return ResponseEntity.ok(cartItems);
    }


    @PutMapping
    public ResponseEntity<CartItem> updateCart(CartItem cartItem){
        cartItemService.updateCart(cartItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartItem> deleteCart(@PathVariable Long id){
        cartItemService.deleteByCartId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/single/{id}")
    public ResponseEntity<CartItem> deleteItem(@PathVariable Long id){
        cartItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }





}
