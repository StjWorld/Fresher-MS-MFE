package com.ordersms.orders.service;

import com.ordersms.orders.entity.OrderProducts;
import com.ordersms.orders.repository.OrderProductsRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductsService {

  @Autowired
  OrderProductsRepo orderProductsRepo;

  public List<OrderProducts> getAll() {
    List<OrderProducts> orderProducts = orderProductsRepo.findAll();
    if (orderProducts.isEmpty()) {
      return new ArrayList<>();
    }
    return orderProductsRepo.findAll();
  }

  public void deleteByOrderId(Long id) {
    orderProductsRepo.deleteById(id);
  }
}
