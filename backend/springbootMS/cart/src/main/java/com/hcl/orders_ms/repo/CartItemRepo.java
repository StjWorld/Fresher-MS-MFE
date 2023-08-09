package com.hcl.orders_ms.repo;

import com.hcl.orders_ms.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
