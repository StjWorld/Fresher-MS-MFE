package com.hcl.orders_ms.repo;

import com.hcl.orders_ms.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.id = ?1")
    void deleteByCart(Long cartId);


}
