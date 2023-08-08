package com.hcl.orders_ms.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemId")
    private Long itemId;

    @Column(name="pId")
    private Long productId;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="cartId")
    private Long cart;

}
