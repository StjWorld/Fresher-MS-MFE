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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
	
	@Autowired
	CartItemService itemService;
	
    @Autowired
    CartRepo cartRepo;

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
    public Cart createCart(Long id, List<JSONObject> inner){
        Cart newCart = new Cart();
        newCart.setId(id);
        newCart.setCartDate(new Date());
        //create List<CartItem>
        ArrayList<CartItem> cartItems = new ArrayList<>();        
        //save cart_items
        inner.forEach(bin->{
        	CartItem item = new CartItem();
        	item.setProductId((long)bin.get("productId"));
        	item.setQuantity(Integer.parseInt(bin.get("quantity").toString()));
        	item.setCart(newCart.getId());
        	cartItems.add(itemService.createItem(item));
		});
        
        newCart.setCartItems(cartItems);
        
        return cartRepo.save(newCart);
    }

    public void deleteCart(Long id) {
        if(cartRepo.findById(id).isPresent()) {
        	List<CartItem> toDelete = itemService.getByCartId(id);
        	toDelete.forEach(item->{
        		itemService.deleteItem(item.getItemId());
        	});
        	cartRepo.deleteById(id);
        }
    }

	public Cart updateCart(Long id, List<JSONObject> inner) {
		//create List<CartItem> from request body
		List<CartItem> toUpdate = new ArrayList<>();
		List<CartItem> toRemove = new ArrayList<>();
		inner.forEach(bin->{
        	CartItem item = new CartItem();
        	item.setProductId((long)bin.get("productId"));
        	item.setQuantity(Integer.parseInt(bin.get("quantity").toString()));
        	item.setCart(id);
        	toUpdate.add(item);
		});
		//find cart by id;
		Optional<Cart> cart = cartRepo.findById(id);
		if(!cart.isPresent()) {
			System.out.println("Cart with id: "+id+" not found");
			return new Cart();
		}else {
			//update time
			cart.get().setCartDate(new Date());
			//find cart items by id
			List<CartItem> items = itemService.getByCartId(id);
			//create HashMap where key=pId, value=itemId
			HashMap<Long,Long> itemMap = new HashMap<Long,Long>();
			items.forEach(x->{
				itemMap.put(x.getProductId(), x.getItemId());
			});
			if(!items.isEmpty()) {
				toUpdate.forEach(bin ->{
					//if cart item exists, saveAndFlush;
					if(itemMap.containsKey(bin.getProductId())) {
						//item quantity moved to zero -> delete item
						if(bin.getQuantity()<1) {
							itemService.deleteItem(itemMap.get(bin.getProductId()));
							toRemove.add(bin);
						}else {
							bin.setItemId(itemMap.get(bin.getProductId()));
							itemService.updateItem(bin);
						}
					}else {
						//if cart item does not exist and has quantity > 0, save;
						if(bin.getQuantity()>0) {
							itemService.createItem(bin);
						}else {
							toRemove.add(bin);
						}
					}
				});
			}
		}
		//remove items with quantity < 1
		toUpdate.removeAll(toRemove);
		//update cart items in cart for response
		cart.get().setCartItems(toUpdate);
		//return cartRepo.saveAndFlush;
		return cartRepo.saveAndFlush(cart.get());
	}

}
