package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;

    private final double discount;

    private final List<Product> products;

    public Order(int id, double discount) {
        this.id = id;
        this.discount = discount;
        this.products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getDiscount() {
        return discount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getFinalPrice() {
        int finalPrice = 0;
        for (Product product : products) {
            finalPrice += product.getFinalPrice();
        }
        return finalPrice - (int) (finalPrice * discount);
    }
}
