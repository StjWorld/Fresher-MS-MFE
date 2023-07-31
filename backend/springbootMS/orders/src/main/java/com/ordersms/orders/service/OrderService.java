package com.ordersms.orders.service;

import com.ordersms.orders.entity.Order;
import com.ordersms.orders.repository.OrderRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepo orderRepo;

  public Order getOrderById(Long id) {
    return orderRepo.findById(id).orElse(null);
  }

  public List<Order> getAllOrders() {
    return orderRepo.findAll();
  }

  public Order createOrder(Order order) {
    return orderRepo.save(order);
  }

  public boolean deleteOrderById(Long id) {
    if (orderRepo.existsById(id)) {
      orderRepo.deleteById(id);
      return true;
    }
    return false;
  }
}
