package com.ordersms.orders.controller;

import com.ordersms.orders.entity.OrderProducts;
import com.ordersms.orders.service.OrderProductsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/orderedproducts")
public class OrderProductsController {

  @Autowired
  OrderProductsService orderProductsService;

  @PostMapping
  public ResponseEntity<List<OrderProducts>> getAll() {
    List<OrderProducts> orderProducts = orderProductsService.getAll();
    return ResponseEntity.ok(orderProducts);
  }
}
