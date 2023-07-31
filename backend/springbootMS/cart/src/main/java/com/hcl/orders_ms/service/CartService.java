package com.hcl.orders_ms.service;

import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.repo.CartItemRepo;
import com.hcl.orders_ms.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    public List<Cart> getAll(){
        List<Cart> ordersList = cartRepo.findAll();
        if (ordersList.size() > 0) {
            return ordersList;
        } else {
            return new ArrayList<Cart>();
        }
    }

    public Optional<Cart> getCartById(Long id) {return cartRepo.findById(id);}


    //Will update the existing data or create a new one.
    public Cart createCart(Cart cart){
        Cart newCart = new Cart();
        newCart.setId(cart.getId());
        newCart.setCartDate(new Date());
        for(CartItem item : cart.getCartItems()){
            CartItem newItem = new CartItem();
            newItem.setQuantity(item.getQuantity());
            newItem.setProductId(item.getProductId());
            newCart.add(newItem);
        }
        return cartRepo.save(newCart);
    }

    public void deleteCart(Long id) {
        if(cartRepo.findById(id).isPresent())
            cartRepo.deleteById(id);
    }

}
