package org.example.service;

import org.example.Model.*;

import java.util.List;

public interface BackofficeService {
    void addProductToShop(ProductDto productDto);

    void createOrder(Discount discount);

    void addProductToOrder(String orderId, String productId);

    double finishOrder(String orderId);

    List<Product> getProducts();

    List<Order> getOrders();

    List<Order> getFinishedOrders();
}
