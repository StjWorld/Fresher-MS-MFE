package com.hcl.orders_ms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cart_item")
@Data
@NoArgsConstructor

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    @JsonIgnore
    private Cart cart;

    @Column
    private long productId;

    @Column
    private int quantity;

    public CartItem(int productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }


}
