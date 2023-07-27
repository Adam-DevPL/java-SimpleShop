package org.example.service;

import org.example.Model.*;

import java.util.*;

public class BackofficeServiceImpl implements BackofficeService {

    private final List<Product> products = new ArrayList<>();

    private final List<Order> orders = new ArrayList<>();

    private final List<Order> finishedOrders = new ArrayList<>();

    @Override
    public void addProductToShop(ProductDto productDto) {
        Product product = new Product.ProductBuilder(productDto).build();
        products.add(product);
    }

    @Override
    public void createOrder(Discount discount) {
        Order order = new Order(discount);
        orders.add(order);
    }

    @Override
    public void addProductToOrder(String orderId, String productId) {
        Product product = products.stream().filter(p -> Objects.equals(p.getId(), productId)).findFirst().orElseThrow(() -> new NoSuchElementException("Product not found"));
        Order order = orders.stream().filter(o -> Objects.equals(o.getId(), orderId)).findFirst().orElseThrow(() -> new NoSuchElementException("Order not found"));
        order.addProduct(product);
    }

    @Override
    public double finishOrder(String orderId) {
        Order order = orders.stream().filter(o -> Objects.equals(o.getId(), orderId)).findFirst().orElseThrow(() -> new NoSuchElementException("Order not found"));

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

}
