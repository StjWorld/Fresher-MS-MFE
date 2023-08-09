package com.hcl.orders_ms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name="carts")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name="cart_id")
    private Long id;

    @Column(name="cart_date")
    private Date cartDate;
    
    @Column(name="cart_contents")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<CartItem> cartItems;    

}
