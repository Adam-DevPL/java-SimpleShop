package org.example.Model;

public class Product {

    private final int id;

    private final String name;

    private final ProductCategory category;

    private final int price;

    private final double tax;

    private final double discount;

    public Product(int id, String name, ProductCategory category, int price, double tax, double discount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getFinalPrice() {
        return price + (int) (price * tax) - (int) (price * discount);
    }
}
