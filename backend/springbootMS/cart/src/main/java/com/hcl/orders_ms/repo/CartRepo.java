package com.hcl.orders_ms.repo;

import com.hcl.orders_ms.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
}
