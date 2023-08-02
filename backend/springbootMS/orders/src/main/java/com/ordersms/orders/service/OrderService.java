package com.ordersms.orders.service;

import com.ordersms.orders.entity.Order;
import com.ordersms.orders.entity.OrderProducts;
import com.ordersms.orders.repository.OrderProductsRepo;
import com.ordersms.orders.repository.OrderRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  private OrderProductsRepo orderProductsRepo;

  public Order getOrderById(Long id) {
    return orderRepo.findById(id).orElse(null);
  }

  public List<Order> getAllOrders() {
    return orderRepo.findAll();
  }

  public Order createOrder(Order order) {
    Order newOrder = new Order();
    newOrder.setId(order.getId());
    newOrder.setOrderDate(order.getOrderDate());
    for (OrderProducts product : order.getOrderProducts()) {
      OrderProducts newOrderProduct = new OrderProducts();
      newOrderProduct.setOrder(newOrder);
      newOrderProduct.setProductId(product.getProductId());
      newOrderProduct.setProductQuantity(product.getProductQuantity());
      newOrder.add(newOrderProduct);
    }
    return orderRepo.save(newOrder);
  }

  public boolean deleteOrderById(Long id) {
    if (orderRepo.existsById(id)) {
      orderRepo.deleteById(id);
      return true;
    }
    return false;
  }

  public Order updateOrder(Long id, Order order) {
    return null;
  }
}
