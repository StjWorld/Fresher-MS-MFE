package com.ordersms.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ordersms.orders.entity.OrderProducts;

@Repository
public interface OrderProductsRepo extends JpaRepository<OrderProducts, Long>{}
