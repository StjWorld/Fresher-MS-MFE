package com.hcl.orders_ms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="carts")
@Data
@NoArgsConstructor
public class Cart {

//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Long id;

    @Column(name="cart_date")
    private Date cartDate;

    @OneToMany(mappedBy = "cart",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<CartItem> cartItems;

    public Cart(Long id, Date cartDate) {
        this.id = id;
        this.cartDate = cartDate;
    }

    public Cart(Long id,Date cartDate, List<CartItem> items) {
        this.id = id;
        this.cartDate = cartDate;
        this.cartItems = items;
    }

    //add convenience methods for bi-directional relationship
    public void add(CartItem item){
        if(cartItems == null)
            cartItems = new ArrayList<>();
        cartItems.add(item);
        item.setCart(this);
    }


}
