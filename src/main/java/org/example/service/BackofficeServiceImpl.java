package org.example.service;

import org.example.Model.Order;
import org.example.Model.Product;
import org.example.Model.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BackofficeServiceImpl implements BackofficeService {

    private final List<Product> products = new ArrayList<>();

    private final HashMap<String, Double> discounts = new HashMap<>();

    private final List<Order> orders = new ArrayList<>();

    private final List<Order> finishedOrders = new ArrayList<>();

    @Override
    public void addProductToShop(int id, String name, ProductCategory category, int price, double tax, double discount) {
        Product product = new Product(id, name, category, price, tax, discount);
        products.add(product);
    }

    @Override
    public void addDiscount(String name, double discount) {
        discounts.put(name, discount);
    }

    @Override
    public double getDiscount(String name) {
        return discounts.get(name);
    }

    @Override
    public void createOrder(int id, double discount) {
        Order order = new Order(id, discount);
        orders.add(order);
    }

    @Override
    public void addProductToOrder(int orderId, int productId) {
        Product product = products.stream().filter(p -> p.getId() == productId).findFirst().orElseThrow();
        Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElseThrow();
        order.addProduct(product);
    }

    @Override
    public int finishOrder(int orderId) {
        Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElseThrow();

        orders.remove(order);
        finishedOrders.add(order);

        return order.getFinalPrice();
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getFinishedOrders() {
        return finishedOrders;
    }

    public HashMap<String, Double> getDiscounts() {
        return discounts;
    }

}
