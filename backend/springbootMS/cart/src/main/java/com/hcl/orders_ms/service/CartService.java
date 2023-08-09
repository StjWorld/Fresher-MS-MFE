package com.hcl.orders_ms.service;

import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.repo.CartItemRepo;
import com.hcl.orders_ms.repo.CartRepo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
	
	@Autowired
	CartItemService itemService;
	
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
        newCart.setCartItems(cart.getCartItems());
        newCart.setCartDate(new Date());
        //create List<CartItem>
//        ArrayList<CartItem> cartItems = new ArrayList<>();        
        //save cart_items
//        inner.forEach(bin->{
//        	CartItem item = new CartItem();
//        	item.setProductId(Long.parseLong((String)bin.get("productId")));
//        	item.setQuantity(Integer.parseInt(bin.get("quantity").toString()));
//        	item.setCart(newCart.getId());
//        	cartItems.add(itemService.updateCart(item));
//		});
        
        
       newCart.getCartItems().forEach(item->{itemService.updateCart(item);});
        
//        newCart.setCartItems(cartItems);
        
        return cartRepo.save(newCart);
        //return cartRepo.save(newCart);
    }

    public void deleteCart(Long id) {
        if(cartRepo.findById(id).isPresent()) {
        	cartRepo.deleteById(id);
        }
            
    }

}
