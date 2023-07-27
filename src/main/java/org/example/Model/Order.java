package org.example.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;

    private final Discount discount;

    private final List<Product> products;

    public Order(Discount discount) {
        this.id = UUID.randomUUID().toString();
        this.discount = discount == null ? Discount.NONE : discount;
        this.products = new ArrayList<>();
    }

    public String getId() {
        return id;
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
