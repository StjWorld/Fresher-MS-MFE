package com.hcl.orders_ms.service;

import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.repo.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;

    public List<CartItem> getAll(){
        List<CartItem> items= cartItemRepo.findAll();
        if(items.isEmpty()){
            return new ArrayList<>();
        }
        return cartItemRepo.findAll();
    }

    public CartItem createItem(CartItem cartItem){
        return cartItemRepo.save(cartItem);
    }
    
    public CartItem updateItem(CartItem cartItem) {
    	return cartItemRepo.saveAndFlush(cartItem);
    }

    public void deleteItem(Long id){
        cartItemRepo.deleteById(id);
    }

    public List<CartItem> getByCartId(Long id){
    	CartItem toSearch = new CartItem();
    	toSearch.setCart(id);
    	Example<CartItem> example = Example.of(toSearch);
    	return cartItemRepo.findAll(example);
    }
    
}
