package com.ordersms.orders.repository;

import com.ordersms.orders.entity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepo extends JpaRepository<OrderProducts, Long> {}
