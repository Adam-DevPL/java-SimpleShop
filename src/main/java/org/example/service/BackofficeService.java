package org.example.service;

import org.example.Model.Order;
import org.example.Model.Product;
import org.example.Model.ProductCategory;

import java.util.HashMap;
import java.util.List;

public interface BackofficeService {
    void addProductToShop(int id, String name, ProductCategory category, int price, double tax, double discount);

    void addDiscount(String name, double discount);

    double getDiscount(String name);

    void createOrder(int id, double discount);

    void addProductToOrder(int orderId, int productId);

    int finishOrder(int orderId);

    List<Product> getProducts();

    List<Order> getOrders();

    List<Order> getFinishedOrders();
    HashMap<String, Double> getDiscounts();
}
